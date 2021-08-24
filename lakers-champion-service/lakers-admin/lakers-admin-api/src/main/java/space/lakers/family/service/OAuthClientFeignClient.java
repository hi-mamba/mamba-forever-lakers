package space.lakers.family.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import space.lakers.family.entity.SysOauthClient;
import space.lakers.utils.vo.DataResponse;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
@FeignClient(value = "lakers-family-service",contextId = "test")
public interface OAuthClientFeignClient {


    /**
     * @param clientId
     * @return
     */
    @GetMapping("/api/v1/oauth-clients/{clientId}")
    DataResponse<SysOauthClient> getOAuthClientById(@PathVariable String clientId);
}
