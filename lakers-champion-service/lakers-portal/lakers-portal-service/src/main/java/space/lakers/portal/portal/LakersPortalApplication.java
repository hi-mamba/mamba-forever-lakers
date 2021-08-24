package space.lakers.portal.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * @author mamba
 */
//@MapperScan(basePackages = "space.mamba.mapper")
@EnableCaching
@SpringBootApplication
//@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy
public class LakersPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(LakersPortalApplication.class, args);
    }
}
