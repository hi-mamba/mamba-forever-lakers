package space.mamba;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liyan16
 * @create 2020/5/9 11:59
 */
@SpringBootApplication(scanBasePackages = "space.mamba")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
