package space.mamba.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.mamba.service.zookeeper.impl.ServiceRegistryImpl;



/**
 * @author pankui
 * @date 2019-05-13
 * <pre>
 *      https://www.jianshu.com/p/0dfac0ad266f
 * </pre>
 */
@Configuration
@ConfigurationProperties(prefix = "registry")
public class ZkRegistryConfig {

    private String servers;

    public String getServers() {
        return servers;
    }

    @Bean
    public ServiceRegistryImpl serviceRegistry() {
        return new ServiceRegistryImpl(servers);
    }

    public void setServers(String servers) {
        this.servers = servers;
    }
}