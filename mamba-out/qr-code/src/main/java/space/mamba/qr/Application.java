package space.mamba.qr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pankui
 */
@SpringBootApplication(scanBasePackages = "space.mamba.qr")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}