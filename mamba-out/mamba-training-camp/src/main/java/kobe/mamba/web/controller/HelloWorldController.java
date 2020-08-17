package kobe.mamba.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mamba
 * @date 2020/8/17 23:26
 */
@RestController
@RequestMapping("/api/test")
public class HelloWorldController {


    @GetMapping("/hi")
    public String hi(String name) {
        return "hi " + name;
    }
}
