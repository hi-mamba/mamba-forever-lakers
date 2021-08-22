package space.mamba.family.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.mamba.family.req.AuthRequest;
import space.mamba.family.resp.UserDTO;
import space.mamba.family.resp.vo.UserVO;
import space.mamba.family.service.UserFeignClient;
import space.mamba.family.service.UsersService;

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
@RequestMapping("/api/lakers/users")
public class UsersController implements UserFeignClient {

    @Resource
    private UsersService usersService;

    @Override
    public UserDTO loadUserByUsername(String username) {
        log.info("### request hi");
        UserDTO userDTO = new UserDTO();
        userDTO.setName("湖人总冠军");
        return userDTO;
    }

    @Override
    public UserVO register(AuthRequest authRequest) {
        return usersService.save(authRequest);
    }
}
