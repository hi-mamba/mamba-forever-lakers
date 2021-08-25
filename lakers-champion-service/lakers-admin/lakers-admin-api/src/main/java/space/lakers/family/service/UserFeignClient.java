package space.lakers.family.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import space.lakers.domain.UserDTO;
import space.lakers.family.req.AuthRequest;
import space.lakers.family.resp.vo.UserVO;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
@FeignClient(value = "lakers-admin-service",path = "/admin/lakers/users")
public interface UserFeignClient {

    /**
     * @param username
     * @return
     */
    @GetMapping("/loadUserByUsername")
    UserDTO loadUserByUsername(@RequestParam(value = "username") String username);

    /**
     * @param authRequest
     * @return
     */
    @PostMapping("/register")
    UserVO register(@RequestBody AuthRequest authRequest);
}
