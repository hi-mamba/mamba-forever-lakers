package space.mamba.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pankui
 * @date 2019-08-09
 * <pre>
 *
 * </pre>
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }
}