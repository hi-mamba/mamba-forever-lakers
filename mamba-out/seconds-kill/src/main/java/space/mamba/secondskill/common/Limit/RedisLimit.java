package space.mamba.secondskill.common.Limit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import space.mamba.secondskill.common.utils.ScriptUtil;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @auther G.Fukang
 * @date 6/7 21:45
 */
@Slf4j
public class RedisLimit {

    private static final int FAIL_CODE = 0;

    private static Long limit = 5L;

    /**
     * Redis 限流
     */
    public static Boolean limit(StringRedisTemplate stringRedisTemplate) {
        try {

            //TODO 目前脚本有问题，null 和数字比较，或者key 值为空

            // 执行 lua 脚本
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            // 指定 lua 脚本
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));
            // 指定返回类型
            redisScript.setResultType(Long.class);
            // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
            // 请求限流
            String key = String.valueOf(System.currentTimeMillis() / 1000);
            // 计数限流
            Long result = stringRedisTemplate.execute(redisScript, Collections.singletonList(key));
            System.out.println(result);
            if (FAIL_CODE != result) {
                log.info("成功获取令牌");
                return true;
            }
        } catch (Exception e) {
            log.error("limit 获取 Jedis 实例失败：", e);
        } finally {
        }
        return false;
    }
}
