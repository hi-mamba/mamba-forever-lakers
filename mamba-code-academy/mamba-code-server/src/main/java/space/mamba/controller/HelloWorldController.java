package space.mamba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.mamba.service.business.UserInfoService;

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
