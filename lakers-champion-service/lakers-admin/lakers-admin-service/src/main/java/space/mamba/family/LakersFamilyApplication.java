package space.mamba.family;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author mamba
 */
@MapperScan(basePackages = "space.mamba.family.mapper")
@EnableCaching
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy
public class LakersFamilyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LakersFamilyApplication.class, args);
	}
}
