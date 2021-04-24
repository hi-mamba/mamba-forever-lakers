package kobe.mamba.example.redisson;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBinaryStream;
import org.redisson.api.RBitSet;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RBucketRx;
import org.redisson.api.RLock;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.config.Config;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author pankui
 * @date 2021/4/20
 * <pre>
 *  https://www.bookstack.cn/read/redisson-doc-cn/config.md
 *
 *   Redisson操作redis
 *  * Redisson除了提供同步接口外，还提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口。
 *  * Redisson会序列化java对象然后保存到reids，所以通
 *
 * </pre>
 */
public class RedissonExample {

    private RedissonClient client;

    private RedissonReactiveClient reactiveClient;

    private RedissonRxClient rxClient;

    @Before
    public void before() {
        Config config = new Config();
        //https://www.cnblogs.com/hetutu-5238/p/12186582.html
        //构建项目的时候可以根据项目指定环境的不同来指定不同的EnventLoopGroup提升性能。
        // 例如开发环境 windows可以使用java的NIO ，mac可以使用netty定制的KqueueSelector.
        // 生产环境一般为linux则可以使用netty定制的EpollSelector来提高负载性能
       // config.setTransportMode(TransportMode.NIO);
        //config.useClusterServers()
              //  .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                //TODO 如果  (error) CLUSTERDOWN Hash slot not served，这里地址包含 salve，需要修改成 master
                //
               // .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
               // .addNodeAddress("redis://127.0.0.1:7002"),
        // 单机
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        client = Redisson.create(config);

        reactiveClient = Redisson.createReactive(config);

        rxClient = Redisson.createRx(config);
    }

    @After
    public void after() {
        client.shutdown();
        reactiveClient.shutdown();
        rxClient.shutdown();
    }


    @Test
    public void bucket() throws ExecutionException, InterruptedException {
        //同步
        RBucket<String> bucket = client.getBucket("name");
        bucket.set("zhaoyun");
        System.out.println(bucket.get());

        //异步
        RBucket<String> bucket2 = client.getBucket("name2");
        bucket2.setAsync("赵云2").get();
        bucket2.getAsync().thenAccept(System.out::println);

        //Reactive
        RBucketReactive<String> bucket3 = reactiveClient.getBucket("name3");
        bucket3.set("赵云3").block();
        bucket3.get().subscribe(System.out::println);

        //RxJava2
        RBucketRx<String> bucket4 = rxClient.getBucket("name4");
        bucket4.set("赵云4").blockingSubscribe();
        bucket4.get().subscribe(System.out::println);

        Thread.sleep(1000 * 5);
    }


    /**
     * 二进制流
     * 提供了InputStream接口和OutputStream接口的实现
     */
    @Test
    public void stream() throws Exception {
        RBinaryStream stream = client.getBinaryStream("stream");
        stream.set("赵云".getBytes());
        OutputStream outputStream = stream.getOutputStream();
        outputStream.write("张飞".getBytes());

        InputStream inputStream = stream.getInputStream();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = inputStream.read(b)) != -1) {
            result.write(b, 0, len);
        }
        System.out.println(result.toString());
    }

    @Test
    public void atomicLong() {
        RAtomicLong atomicLong = client.getAtomicLong("atomicLong");
        atomicLong.set(10);
        atomicLong.incrementAndGet();
        System.out.println(atomicLong);
    }

    /**
     * 限流器
     */
    @Test
    public void rateLimiter() throws InterruptedException {
        RRateLimiter rateLimiter = client.getRateLimiter("rateLimiter");
        //初始化 最大流速:每1秒钟产生5个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 5, 1, RateIntervalUnit.SECONDS);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                int i = 0;
                @Override
                public void run() {
                    while(true) {
                        rateLimiter.acquire(1);
                        System.out.println(Thread.currentThread() + "-" + System.currentTimeMillis() + "-" + i++);
                    }
                }
            }).start();
        }

        Thread.sleep(1000 * 5);
    }



    /**
     * 可重入锁 RLock实现了java.util.concurrent.locks.Lock接口
     */
    @Test
    public void lock() throws InterruptedException {
        RLock lock = client.getLock("lock");
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread() + "-" + System.currentTimeMillis() + "-" + "获取了锁");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("#" + Thread.currentThread().getName() + "," + lock.isLocked());
                    lock.unlock();
                }
            }).start();
        }
        Thread.sleep(1000 * 5);
    }

    @Test
    public void lock2() throws InterruptedException {
        RLock lock = client.getLock("lock");

        Executor executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 8; i++) {
            int finalI = i;
            executor.execute(() -> {
                boolean getLock = lock.tryLock();
                try {
                    if (getLock) {
                        System.out.println(Thread.currentThread() + "-" + System.currentTimeMillis() + "-" + "获取了锁");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(finalI + "#########" + Thread.currentThread().getName() + "," + getLock);
                    if (lock.isLocked()) {
                        System.out.println(finalI + "释放锁 ####" + Thread.currentThread().getName());
                        lock.unlock();
                    }
                }
            });
        }
        Thread.sleep(1000 * 7);
    }


    @Test
    public void bitSet() {
        RBitSet bitSet = client.getBitSet("bs");
        bitSet.expire(10, TimeUnit.SECONDS);

        bitSet.set(0, true);
        bitSet.set(10, true);
        bitSet.set(20, true);
        bitSet.set(30, true);

        System.out.println(bitSet.get(0));
        System.out.println(bitSet.get(10));
        System.out.println(bitSet.get(30));
    }



    /**
     * Redisson利用Redis实现了Java分布式布隆过滤器（Bloom Filter）
     */
    @Test
    public void bf() {
        RBloomFilter<String> bf = client.getBloomFilter("qq");
        if (!bf.isExists()) {
            bf.tryInit(150000000L, 0.05);
            bf.add("test");
            bf.expire(200, TimeUnit.SECONDS);
        }
        bf.add("https://www.baidu.com/");
        bf.add("https://www.tmall.com/");
        bf.add("https://www.jd.com/");
        System.out.println(bf.contains("https://www.tmall.com/"));
        System.out.println(bf.count());
    }
}
