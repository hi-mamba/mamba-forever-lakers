package space.mamba.auth.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import space.mamba.auth.config.JwtUtil;
import space.mamba.family.req.AuthRequest;
import space.mamba.family.resp.AuthResponse;
import space.mamba.family.resp.vo.UserVO;
import space.mamba.family.service.UserFeignClient;

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