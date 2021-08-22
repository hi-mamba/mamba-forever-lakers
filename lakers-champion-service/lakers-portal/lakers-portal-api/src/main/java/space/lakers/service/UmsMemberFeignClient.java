package space.lakers.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import space.mamba.domain.UserDTO;

/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */
@FeignClient(value = "lakers-portal", path = "/api/sso")
public interface UmsMemberFeignClient {

    /**
     * @param username
     * @return
     */
    @GetMapping("/sso/loadUserByUsername")
    UserDTO loadUserByUsername(@RequestParam(value = "username") String username);
}
