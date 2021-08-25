package space.lakers.family.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.lakers.domain.UserDTO;
import space.lakers.family.componet.LoginUserHolder;
import space.lakers.family.req.AuthRequest;
import space.lakers.family.resp.vo.UserVO;
import space.lakers.family.service.UserFeignClient;
import space.lakers.family.service.UsersService;

import javax.annotation.Resource;

/**
 * @author pankui
 * @date 2021/8/14
 * <pre>
 *
 * </pre>
 */
@Slf4j
@RestController
@RequestMapping("/admin/lakers/users")
public class UsersController implements UserFeignClient {

    @Resource
    private UsersService usersService;

    @Override
    public UserDTO loadUserByUsername(String username) {
        log.info("### request hi");
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("湖人总冠军");
        return userDTO;
    }

    @Override
    public UserVO register(AuthRequest authRequest) {
        return usersService.save(authRequest);
    }


    @Resource
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }
}
