package space.lakers.family.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.lakers.domain.UserDTO;
import space.lakers.family.componet.LoginUserHolder;
import space.lakers.family.model.Users;
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
    private LoginUserHolder loginUserHolder;

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

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }

    @GetMapping("/get")
    public Users getById(Long id) {
       // return usersService.getById(id);
        log.info("##### get ={}", id);
        return new Users();
    }
}
