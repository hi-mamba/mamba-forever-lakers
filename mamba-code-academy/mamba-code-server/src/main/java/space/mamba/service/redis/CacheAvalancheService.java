package space.mamba.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *      缓存雪崩问题：
 *      缓存在同一时间内大量键过期（失效），接着来的一大波请求瞬间都落在了数据库中导致连接异常。
 *
 *     关于缓存崩溃的解决方法，这里提出了三种方案：使用锁或队列、设置过期标志更新缓存、为key设置不同的缓存失效时间，
 *     还有一各被称为“二级缓存”的解决方法，有兴趣的读者可以自行研究。
 *
 * 如何避免？https://www.cnblogs.com/fidelQuan/p/4543387.html
 *     1：在缓存失效后，通过加锁或者队列来控制读数据库写缓存的线程数量。比如对某个key只允许一个线程查询数据和写缓存，
 *     其他线程等待。
 *
 *     2：不同的key，设置不同的过期时间，让缓存失效的时间点尽量均匀。
 *
 *     3：做二级缓存，A1为原始缓存，A2为拷贝缓存，A1失效时，可以访问A2，A1缓存失效时间设置为短期，A2设置为长期（此点为补充）
 *
 *
 *
 *
 *
 *
 *
 * </pre>
 */
@Service
public class CacheAvalancheService {


    @Autowired
    private RedisTemplate<String, Object> template;

    /**
     * 加锁排队
     * <p>
     * 加锁排队只是为了减轻数据库的压力，并没有提高系统吞吐量。
     * 假设在高并发下，缓存重建期间key是锁着的，这是过来1000个请求999个都在阻塞的。
     * 同样会导致用户等待超时，这是个治标不治本的方法！
     * <p>
     * 注意：加锁排队的解决方式分布式环境的并发问题，有可能还要解决分布式锁的问题；线程还会被阻塞，用户体验很差！
     * 因此，在真正的高并发场景下很少使用！
     */
    public Object getProjectList() {
        int cache = 30;
        String cacheKey = "cacheKey";
        String lockKey = "lockKey";

        Object value = template.opsForValue().get(cacheKey);
        if (value != null) {
            return value;
        }

        synchronized (lockKey) {
            value = template.opsForValue().get(cacheKey);
            if (value != null) {
                return value;
            }
            //这里一般是sql查询数据// 从数据库查询数据
            value = "select data from database";
            template.opsForValue().set(cacheKey, value, cache, TimeUnit.SECONDS);
            return value;
        }
    }

    /**
     * 队列、设置过期标志更新缓存
     * <p>
     * 还有一个解决办法解决方案是：给每一个缓存数据增加相应的缓存标记，
     * 记录缓存的是否失效，如果缓存标记失效，则更新数据缓存，实例伪代码如下
     */
    public Object getProductListNew() {
        int cacheTime = 30;
        String cacheKey = "product_list";
        //缓存标记
        String cacheSign = cacheKey + "_sign";

        Object sign = template.opsForValue().get(cacheSign);
        //获取缓存值
        Object cacheValue = template.opsForValue().get(cacheKey);
        if (sign != null) {
            //未过期，直接返回
            return cacheValue;
        } else {
            template.opsForValue().set(cacheSign, "1", cacheTime, TimeUnit.SECONDS);

            // 下面的缓存异步队列去执行
            //  Queue<YourObject> queue = new ConcurrentLinkedQueue<YourObject>();

            // TODO 这里查询数据库到结束，请缓存队列去执行
            //这里一般是 sql查询数据
            cacheValue = "GetProductListFromDB()";
            //日期设缓存时间的2倍，用于脏读
            template.opsForValue().set(cacheKey, cacheValue, cacheTime * 2, TimeUnit.SECONDS);


            return cacheValue;
        }
    }


    /**
     * 二级缓存
     * */

}
