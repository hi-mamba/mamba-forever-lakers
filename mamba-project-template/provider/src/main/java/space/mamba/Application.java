package space.mamba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author liyan16
 * @create 2020/5/9 11:59
 */
@EnableCaching
@SpringBootApplication(scanBasePackages = "space.mamba")
@EnableTransactionManagement(proxyTargetClass = true)
//@MapperScan(basePackages = "space.mamba")
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
