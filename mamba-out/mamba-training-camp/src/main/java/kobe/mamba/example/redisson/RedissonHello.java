package kobe.mamba.example.redisson;


import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.TimeUnit;

/**
 * @author pankui
 * @date 2021/4/20
 * <pre>
 *  https://www.bookstack.cn/read/redisson-doc-cn/config.md
 * </pre>
 */
public class RedissonHello {

    public static void main(String[] args) {
        Config config = new Config();
        //https://www.cnblogs.com/hetutu-5238/p/12186582.html
        //构建项目的时候可以根据项目指定环境的不同来指定不同的EnventLoopGroup提升性能。
        // 例如开发环境 windows可以使用java的NIO ，mac可以使用netty定制的KqueueSelector.
        // 生产环境一般为linux则可以使用netty定制的EpollSelector来提高负载性能
        //config.setTransportMode(TransportMode.NIO);
        config.useClusterServers()
                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
                .addNodeAddress("redis://127.0.0.1:7002");

        RedissonClient redissonClient = Redisson.create(config);


        RSet<String> set = redissonClient.getSet("set");
        set.add("1");
        set.add("2");
        set.add("3");
        set.forEach(System.out::println);


        RLock rLock = redissonClient.getLock("test");
        rLock.lock(5, TimeUnit.SECONDS);

        rLock.unlock();

        System.out.println("### ");

        RAtomicLong rAtomicLong = redissonClient.getAtomicLong("myLong");
        rAtomicLong.compareAndSet(3, 10);

        System.out.println("---");
    }
}
