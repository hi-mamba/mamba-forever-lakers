package space.mamba.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * @author mamba
 */
@EnableFeignClients(basePackages = "space.mamba")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages = "space.mamba")
@EnableAspectJAutoProxy
public class LaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaAuthApplication.class, args);
    }
}
