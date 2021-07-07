package space.mamba.service.redis;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import space.mamba.dao.UserDao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *      缓存击穿问题
 *
 *      https://juejin.im/post/5c9442ae5188252d77392241
 *
 *      https://juejin.im/post/5b961172f265da0ab7198f4d
 *
 *      https://www.cnblogs.com/rjzheng/p/8908073.html
 *
 *      如果黑客每次故意查询一个在缓存内必然不存在的数据，导致每次请求都要去存储层去查询，这样缓存就失去了意义。
 *      如果在大流量下数据库可能挂掉。这就是缓存击穿。
 *
 *      在这里我们给出三套解决方案
 *
 *      - 使用互斥锁
 *      - 异步构建缓存
 *      - 布隆过滤器
 *
 * </pre>
 */
@Slf4j
@Service
public class CacheBreakdownService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisBasicService redisBasicService;

    ExecutorService execute = Executors.newFixedThreadPool(10);

    /**
     * 使用互斥锁:单机环境用并发包的Lock类型就行，集群环境则使用分布式锁( redis的setnx)
     * <p>
     * 这样做思路比较清晰，也从一定程度上减轻数据库压力，但是锁机制使得逻辑的复杂度增加，吞吐量也降低了，有点治标不治本。
     */
    public String getForLock(String key) {

        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            System.out.println("### 数据库不存在,开始抢锁！" + Thread.currentThread().getName());
            String keyMutex = "key_mutex" + key;
            if (redisBasicService.addRedisLock(keyMutex, 10)) {
                System.out.println("### 获取锁。。" + Thread.currentThread().getName());
                // 3 min timeout to avoid mutex holder crash
                redisTemplate.expire(key, 3 * 60, TimeUnit.SECONDS);
                // 从数据库获取数据
                value = "get_data_from_db";
                redisTemplate.opsForValue().set(key, value);
                redisTemplate.delete(keyMutex);
                System.out.println("### 删除。。key" + keyMutex + "," + Thread.currentThread().getName());
            } else {
                System.out.println("## 没有获取锁。。线程休息50毫秒后重试" + Thread.currentThread().getName());
                //其他线程休息50毫秒后重试
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getForLock(key);
            }
        } else {
            System.out.println("### 缓存已经存在" + Thread.currentThread().getName());
        }
        return value == null ? null : value.toString();
    }


    /**
     * 我们把过期时间存在key对应的value里，如果发现要过期了，通过一个后台的异步线程进行缓存的构建，也就是“逻辑”过期
     * <p>
     * 不过期(本文)【异步构建缓存，不会阻塞线程池】
     * <p>
     * 缺点：
     * 1. 不保证一致性。
     * <p>
     * 2. 代码复杂度增大(每个value都要维护一个timekey)。
     * <p>
     * 3. 占用一定的内存空间(每个value都要维护一个timekey)。
     *
     *
     * https://www.cnblogs.com/raichen/p/7750165.html
     *
     */
    String get(final String key) {
        Object value = redisTemplate.opsForValue().get(key);
        // String value = v.getValue();
        long timeout = 0;//v.getTimeout();
        if (timeout <= System.currentTimeMillis()) {
            // 异步更新后台异常执行
            execute.execute(() -> {
                String keyMutex = "mutex:" + key;
                if (redisBasicService.addRedisLock(keyMutex, 2)) {
                    // 3 min timeout to avoid mutex holder crash
                    redisTemplate.expire(keyMutex, 3 * 60, TimeUnit.SECONDS);
                    Object dbValue = redisTemplate.opsForValue().get(key);
                    redisTemplate.opsForValue().set(key, dbValue);
                    redisTemplate.delete(keyMutex);
                }
            });
            //return value;
        }
        return null;
    }


        /**
         * test bloom filer
         */
        final int count = 500000;
        List<String> stringList = new ArrayList<>(count);
        Set<String> stringSet = new HashSet<>();
        /**
         * 创建布隆过滤器 初始化过滤器数据
         *
         * 并不是设置的误判率越小越好,误判率越小代表需要进行hash计算次数越多消耗资源越多,应根据实际情况进行决策
         *
         */
        private BloomFilter<String> testBloomString =
                BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), count, 0.001);

        public void testBloomFilter () {
            for (int i = 0; i < count; i++) {
                String id = UUID.randomUUID().toString();
                stringList.add(id);
                stringSet.add(id);
                testBloomString.put(id);
            }

            int wrong = 0;
            int right = 0;
            for (int i = 0; i < count; i++) {
                String checkString = i % 100 == 0 ? stringList.get(i) : UUID.randomUUID().toString();
                //布隆过滤器 进过hash算法和byte数组 校验是否存在于集合中
                if (testBloomString.mightContain(checkString)) {
                    //校验是否误判
                    if (stringSet.contains(checkString)) {
                        right++;
                    } else {
                        wrong++;
                    }
                }
            }
            System.out.println("50万测试数据-->共抵挡: " + (count - wrong - right) + "次非法入侵" + "    误判" + wrong);
        }

        /**
         * 创建布隆过滤器 初始化过滤器数据  默认1%误差
         */
        BloomFilter<String> bloomString = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), count, 0.001);

        /**
         * 布隆过滤器（推荐）
         */
        public void bloomFilter () {

            //随机产生一个字符串
            String randomUser = UUID.randomUUID().toString();
//          String randomUser = allUsers.get(new Random().nextInt(allUsers.size())).getUserName();
            String key = "Key:" + randomUser;

            //如果布隆过滤器中不存在这个用户直接返回，将流量挡掉
            if (!bloomString.mightContain(randomUser)) {
                System.out.println("bloom filter don't has this user");
                return;
            }
            //查询缓存，如果缓存中存在直接返回缓存数据
            ValueOperations<String, Object> operation = (ValueOperations<String, Object>) redisTemplate.opsForValue();
            synchronized (this) {
                Object cacheUser = operation.get(key);
                if (cacheUser != null) {
                    System.out.println("return user from redis");
                    return;
                }
                //如果缓存不存在查询数据库
                List<String> user = null;//UserDao.getUserByUserName(randomUser);
                if (user == null || user.size() == 0) {
                    return;
                }
                //将mysql数据库查询到的数据写入到redis中
                System.out.println("write to redis");
                //暂时注释  operation.set("Key:" + user.get(0).getUserName(), user.get(0).getUserName());
            }
        }

        //将数据从数据库导入到本地
        // https://www.jianshu.com/p/b60b0a3e8b9c
        @PostConstruct
        private void init () {
            //将数据从数据库导入到本地
            List<String> allUsers = null;//UserDao.getAllUser();
            if (allUsers == null || allUsers.size() == 0) {
                return;
            }
            //创建布隆过滤器(默认3%误差)
            bloomString = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), allUsers.size());
            //将数据存入布隆过滤器
            for (String userDto : allUsers) {
                // userDat.getUserName();
                bloomString.put("");
            }
        }


    }
