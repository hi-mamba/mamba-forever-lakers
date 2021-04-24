package kobe.mamba.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author mamba
 * @ 2020/7/22
 */

@EntityScan(basePackages = "kobe.mamba")
@SpringBootApplication(scanBasePackages = "kobe.mamba")
public class SpringBootDroolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDroolsApplication.class, args);
    }
}
