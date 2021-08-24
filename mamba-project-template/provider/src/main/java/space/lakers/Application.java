package space.lakers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author mamba
 */
@EnableCaching
@SpringBootApplication(scanBasePackages = "space.lakers")
@EnableTransactionManagement(proxyTargetClass = true)
//@MapperScan(basePackages = "space.mamba")
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
