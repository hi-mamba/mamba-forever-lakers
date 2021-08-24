package space.lakers.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.lakers.auth.domain.Oauth2TokenDTO;
import space.lakers.constant.AuthConstant;
import space.lakers.utils.vo.DataResponse;

import java.security.Principal;
import java.util.Map;


/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */

/**
 * 自定义Oauth2获取令牌接口
 * Created by macro on 2020/7/17.
 * <p>
 * 认证中心登录认证
 */
@RestController
//@Api(tags = "AuthController", description = "认证中心登录认证")
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    //@ApiOperation("Oauth2获取token")
    // @ApiImplicitParams({
    //         @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
    //         @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID", required = true),
    //         @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端秘钥", required = true),
    //         @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
    //         @ApiImplicitParam(name = "username", value = "登录用户名"),
    //         @ApiImplicitParam(name = "password", value = "登录密码")
    // })
    @PostMapping(value = "/token")
    public DataResponse<Oauth2TokenDTO> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDTO oauth2TokenDto = Oauth2TokenDTO.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        return new DataResponse<>(oauth2TokenDto);
    }
}