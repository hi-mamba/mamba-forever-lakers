package space.mamba.secondskill.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import space.lakers.config.RedisConfig;

/**
 * @author pankui
 * @date 2021/10/14
 * <pre>
 *
 * </pre>
 */
@Configuration
@Import(RedisConfig.class)
public class ImportConfig {
}
