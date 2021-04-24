package kobe.mamba.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author pankui
 * @date 2021/4/24
 * <pre>
 *
 * </pre>
 */
@Slf4j
@Service
public class RedisExampleService {

    @Resource
    private RedisTemplate redisTemplate;

    public void hello() {
        redisTemplate.opsForValue().set("test","11");
        redisTemplate.expire("test", 10, TimeUnit.SECONDS);

        log.info("### ={}", redisTemplate.opsForValue().get("test"));
    }
}
