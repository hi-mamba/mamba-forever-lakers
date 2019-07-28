package space.mamba.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import space.mamba.ServiceApplicationTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *      PS : REDIS SERVER 查询不到下面的key:testKey
 * </pre>
 */
@Slf4j
public class RedisConfigTest extends ServiceApplicationTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSetKeyValue() {
        String key = "testKey";
        redisTemplate.opsForValue().set(key, "hello world", 10, TimeUnit.MINUTES);
        Object object = redisTemplate.opsForValue().get(key);
        //   log.info("## {}", object);
        System.out.println(object);


        Object obj = stringRedisTemplate.opsForValue().get("test");
        System.out.println("#" + obj);
    }
}
