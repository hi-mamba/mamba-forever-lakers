package space.mamba.config.database.sharding;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

/**
 * @author pankui
 * @date 2019/9/14
 * <pre>
 *
 * </pre>
 */
public class SnowflakeShardingKeyGeneratorUtil {

    private static final SnowflakeShardingKeyGenerator SNOWFLAKE_SHARDING_KEY_GENERATOR
            = new SnowflakeShardingKeyGenerator();

    public static Long generateKey() {
        return (Long) SNOWFLAKE_SHARDING_KEY_GENERATOR.generateKey();
    }
}
