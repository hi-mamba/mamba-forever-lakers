package space.mamba.family.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import space.mamba.family.req.AuthRequest;
import space.mamba.family.resp.vo.UserVO;
import space.mamba.family.resp.UserDTO;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
@FeignClient(value = "lakers-family-sso",path = "/api/lakers/users")
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
