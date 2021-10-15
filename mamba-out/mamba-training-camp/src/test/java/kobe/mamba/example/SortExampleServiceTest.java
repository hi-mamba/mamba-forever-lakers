package kobe.mamba.example;

import kobe.mamba.example.redis.RedisExampleService;
import kobe.mamba.example.redis.SortExampleService;
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
public class SortExampleServiceTest extends AbstractBaseApiTest {

    @Resource
    private SortExampleService sortExampleService;

    @Test
    public void testSortClass() {
        sortExampleService.sortClass();
    }
}
