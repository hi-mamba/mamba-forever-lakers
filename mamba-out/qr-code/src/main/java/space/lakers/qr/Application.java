package space.lakers.qr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mini kobe
 */
@SpringBootApplication(scanBasePackages = "space.lakers.qr")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
