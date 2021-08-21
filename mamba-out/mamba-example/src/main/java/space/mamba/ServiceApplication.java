package space.mamba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *  @SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
 * </pre>
 */
@MapperScan("space.mamba.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
