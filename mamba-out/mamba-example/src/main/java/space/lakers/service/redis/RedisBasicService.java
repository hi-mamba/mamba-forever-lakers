package space.lakers.service.redis;

import io.lettuce.core.SetArgs;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author mini kobe
 * @date 2019-07-28
 * <pre>
 *      基本操作
 * </pre>
 */
@Service
public class RedisBasicService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取锁
     * */
    public boolean addRedisLock(String lockKey, long expireTime) {
        return stringRedisTemplate.opsForValue()
                .setIfAbsent(lockKey, lockKey, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 实现lock
     */
    public boolean setNx(String key, String value, long expiredTime) {
        Boolean resultBoolean = null;
        try {
            resultBoolean = stringRedisTemplate.execute((RedisCallback<Boolean>) connection -> {
                Object nativeConnection = connection.getNativeConnection();
                String redisResult = "";
                @SuppressWarnings("unchecked")
                RedisSerializer<String> stringRedisSerializer = (RedisSerializer<String>) stringRedisTemplate.getKeySerializer();
                //lettuce连接包下序列化键值，否知无法用默认的ByteArrayCodec解析
                byte[] keyByte = stringRedisSerializer.serialize(key);
                byte[] valueByte = stringRedisSerializer.serialize(value);
                // lettuce连接包下 redis 单机模式setnx
                if (nativeConnection instanceof RedisAsyncCommands) {
                    RedisAsyncCommands commands = (RedisAsyncCommands) nativeConnection;
                    //同步方法执行、setnx禁止异步
                    redisResult = commands
                            .getStatefulConnection()
                            .sync()
                            .set(keyByte, valueByte, SetArgs.Builder.nx().ex(expiredTime));
                }
                // lettuce连接包下 redis 集群模式setnx
                if (nativeConnection instanceof RedisAdvancedClusterAsyncCommands) {
                    RedisAdvancedClusterAsyncCommands clusterAsyncCommands = (RedisAdvancedClusterAsyncCommands) nativeConnection;
                    redisResult = clusterAsyncCommands
                            .getStatefulConnection()
                            .sync()
                            .set(keyByte, keyByte, SetArgs.Builder.nx().ex(expiredTime));
                }
                //返回加锁结果
                return "OK".equalsIgnoreCase(redisResult);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBoolean != null && resultBoolean;
    }
}
