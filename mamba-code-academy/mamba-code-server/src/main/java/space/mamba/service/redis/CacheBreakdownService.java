package space.mamba.service.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *      缓存击穿问题
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
@Service
public class CacheBreakdownService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisBasicService redisBasicService;

    /**
     * 使用互斥锁:单机环境用并发包的Lock类型就行，集群环境则使用分布式锁( redis的setnx)
     */
    public String getForLock(String key) throws InterruptedException {

        redisTemplate.opsForValue().set("test123A", "test1");
        redisTemplate.opsForValue().set("test1232B", "test2");
        redisTemplate.opsForValue().set("test1233C", "test3");

        Set<String> keys = redisTemplate.keys("*");
        System.out.println("### keys size=" + keys.size());
        keys.forEach(System.out::println);



        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            System.out.println("### 数据库不存在...");
            String keyMutex = "key_mutex" + key;
            if (redisBasicService.addRedisLock(keyMutex, 10)) {
                // 3 min timeout to avoid mutex holder crash
                redisTemplate.expire(key, 3 * 60, TimeUnit.SECONDS);
                // 从数据库获取数据
                value = "get_data_from_db";
                redisTemplate.opsForValue().set(key, value);
                redisTemplate.delete(keyMutex);
            } else {
                //其他线程休息50毫秒后重试
                TimeUnit.MILLISECONDS.sleep(50);
                getForLock(key);
            }
        }
        return value == null ? null : value.toString();
    }
}
