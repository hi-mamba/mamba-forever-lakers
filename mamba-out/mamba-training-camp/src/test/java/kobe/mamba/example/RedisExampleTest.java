package kobe.mamba.example;

import kobe.mamba.example.redis.RedisExampleService;
import kobe.mamba.web.base.AbstractBaseApiTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author mini kobe
 * @date 2021/4/24
 * <pre>
 *
 * </pre>
 */
public class RedisExampleTest extends AbstractBaseApiTest {

    @Resource
    private RedisExampleService redisExampleService;

    @Test
    public void testHello() {
        redisExampleService.hello();
    }
}
