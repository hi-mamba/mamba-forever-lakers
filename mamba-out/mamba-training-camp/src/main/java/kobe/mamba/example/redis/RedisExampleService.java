package kobe.mamba.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author mini kobe
 * @date 2021/4/24
 * <pre>
 *              redisTemplate.opsForValue();//操作字符串
 *
 *              redisTemplate.opsForHash();//操作hash
 *
 *              redisTemplate.opsForList();//操作list
 *
 *              redisTemplate.opsForSet();//操作set
 *
 *              redisTemplate.opsForZSet();//操作有序set
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
