package space.mamba.service.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import space.mamba.ServiceApplicationTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *
 * </pre>
 */
@Slf4j
public class CacheBreakdownServiceTest extends ServiceApplicationTest {

    @Autowired
    private CacheBreakdownService cacheBreakdownService;

    ExecutorService executor = Executors.newFixedThreadPool(10);

    @Test
    public void testGetForLock() throws InterruptedException {
        String key = "key_getForLock";

        String value = null;
        for (int i = 0; i < 2; i++) {
            executor.submit(() -> {
                cacheBreakdownService.getForLock(key);
            });
        }

        System.out.println("CacheBreakdownServiceTest value=" + value);
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void testBoolFilter(){
        cacheBreakdownService.testBloomFilter();
    }
}

