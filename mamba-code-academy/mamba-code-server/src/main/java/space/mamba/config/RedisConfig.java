package space.mamba.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Set;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *  @EnableCaching 开启缓存支持
 *
 *  https://blog.csdn.net/fly_leopard/article/details/81029144
 *
 *  https://blog.csdn.net/zhaoheng314/article/details/81564166
 *
 *  调用的时候，必须使用
 *
 *     @Resource
 *     private RedisTemplate<String, Object> redisTemplate;
 *
 *
 *     RedisTemplate< String, Object>注入时用到了@Autowired，@Autowired默认按照类型装配的。
 *     也就是说，想要获取RedisTemplate< String, Object>的Bean，要根据名字装配。
 *     那么自然想到使用@Resource，它默认按照名字装配
 *
 *
 * 在使用SpringBoot时，注入redisTemplate<T, T>类型，会报错(前提是如果这的泛型T不是Object或者String类型)。
 *
 * 在Spring自动配置时注入了两个redisTemplate，分别是StringRedisTemplate和RedisTemplate<Object, Object>.
 * 所以当使用的泛型类型不是自动注入的这"两个类型"时需要自定义redisTemplate bean或者改成上述两个已注入的【使用 @Resource，它默认按照名字装配】。
 *
 * StringRedisTemplate类的父类就是RedisTemplate< String, String>，而Bean默认是单例的，两个是自然是同一个对象了。
 *
 * </pre>
 */
@Configurable
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(target.getClass().getName());
            stringBuilder.append(method.getName());
            for (Object object : params) {
                stringBuilder.append(object.toString());
            }
            return stringBuilder.toString();
        };
    }

    /**
     * 缓存管理器
     */
    @Override
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(lettuceConnectionFactory);

        Set<String> cacheNames = Set.of("codeNameCache");
        builder.initialCacheNames(cacheNames);
        return builder.build();
    }

    /**
     * RedisTemplate配置
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        /**
         * key序列化
         * */
        redisTemplate.setKeySerializer(stringSerializer);
        /**
         * value序列化
         * */
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        /**
         * Hash key序列化
         * */
        redisTemplate.setHashKeySerializer(stringSerializer);
        /**
         * Hash value序列化
         * */
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
