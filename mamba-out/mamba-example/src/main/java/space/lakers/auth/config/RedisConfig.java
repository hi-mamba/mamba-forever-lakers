package space.lakers.auth.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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
 *
 *
 * StringRedisTemplate和RedisTemplate的区别 https://www.nonelonely.com/article/1556289630491
 *
 * StringRedisTemplate是RedisTemplate的子类，StringRedisTemplate中的问和value都是字符串，采用的序列化方案是StringRedisSerializer，
 * 而RedisTemplate则可以用来操作对象，RedisTemplate采用的序列化方案是JdkSerializationRedisSerializer。
 * 无论是StringRedisTemplate还是RedisTemplate，操作Redis的方法都是一致的．
 * StringRedisTemplate和RedisTemplate都是通过opsForValue、opsForZSet或者opsForSet等方法首先获取一个操作对象，再使用该操作对象完成数据的读写。
 *
 * </pre>
 */
@Configuration
@EnableCaching //启用缓存
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 自定义缓存key的生成策略。默认的生成策略是看不懂的(乱码内容) 通过Spring 的依赖注入特性进行自定义的配置注入并且此类是一个配置类可以更多程度的自定义配置
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 缓存配置管理器
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        //以锁写入的方式创建RedisCacheWriter对象
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(factory);
        /*
        设置CacheManager的Value序列化方式为JdkSerializationRedisSerializer,
        但其实RedisCacheConfiguration默认就是使用
        StringRedisSerializer序列化key，
        JdkSerializationRedisSerializer序列化value,
        所以以下注释代码就是默认实现，没必要写，直接注释掉
         */
        // RedisSerializationContext.SerializationPair pair =
        // RedisSerializationContext.SerializationPair.fromSerializer(
        // new JdkSerializationRedisSerializer(this.getClass().getClassLoader()));
        // RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        //创建默认缓存配置对象
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheManager cacheManager = new RedisCacheManager(writer, config);
        return cacheManager;
    }


    /**
     * 获取缓存操作助手对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);// key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
        redisTemplate.setHashKeySerializer(stringSerializer);// Hash key序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
