package space.lakers.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.lakers.domain.UserDTO;
import space.lakers.family.service.UserFeignClient;

import javax.annotation.Resource;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserFeignClient userFeignClient;

    @GetMapping("loadUserByUsername")
    public Object loadUsername() {
        UserDTO userDTO = userFeignClient.loadUserByUsername("admin");
        return userDTO;
    }

}