package space.lakers.auth.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import space.lakers.auth.config.JwtUtil;
import space.lakers.family.req.AuthRequest;
import space.lakers.family.resp.AuthResponse;
import space.lakers.family.resp.vo.UserVO;
import space.lakers.family.service.UserFeignClient;

import javax.annotation.Resource;

/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */


@Service
public class AuthService {

    @Resource
    private JwtUtil jwt;

    @Resource
    private UserFeignClient userFeignClient;

    public AuthResponse register(AuthRequest authRequest) {
        //do validation if user already exists
        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));

        UserVO userVO = userFeignClient.register(authRequest);
        Assert.notNull(userVO, "Failed to register user. Please try again later");

        String accessToken = jwt.generate(userVO, "ACCESS");
        String refreshToken = jwt.generate(userVO, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);

    }
}