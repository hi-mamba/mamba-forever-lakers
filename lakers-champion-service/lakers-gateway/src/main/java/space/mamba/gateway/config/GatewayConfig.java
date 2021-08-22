package space.mamba.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */
@Configuration
public class GatewayConfig {

    @Resource
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("lakers-family-service", r -> r.path("/lakers/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://lakers-family-service"))

                .route("lakers-auth", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://lakers-auth"))
                .build();
    }
}
