package space.lakers.portal.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import space.lakers.domain.UserDTO;

/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */
@FeignClient(value = "lakers-portal-service", path = "/api/lakers/sso")
public interface UmsMemberFeignClient {

    /**
     * @param username
     * @return
     */
    @GetMapping("/loadUserByUsername")
    UserDTO loadUserByUsername(@RequestParam(value = "username") String username);
}
