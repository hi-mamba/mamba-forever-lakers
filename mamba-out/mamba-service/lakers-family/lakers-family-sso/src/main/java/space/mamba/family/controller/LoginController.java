package space.mamba.family.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.mamba.family.model.dto.LoginRequest;
import space.mamba.family.utils.JwtUtils;
import space.mamba.utils.vo.DataResponse;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
@RestController
@RequestMapping("/api/lakers/login")
public class LoginController {
    /**
     * 30      * Apollo 或 Nacos
     * 31
     */
    @Value("${secretKey:123456}")
    private String secretKey;

    // @Autowired
    // private StringRedisTemplate stringRedisTemplate;

    /**
     * 39      * 登录
     * 40
     */
    @PostMapping("/login")
    public DataResponse login(@RequestBody @Validated LoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return DataResponse.fail(bindingResult.toString());
        }

        String username = request.getUsername();
        String password = request.getPassword();
        //  假设查询到用户ID是1001
        String userId = "1001";
        if ("hello".equals(username) && "world".equals(password)) {
            //  生成Token
            String token = JwtUtils.generateToken(userId, secretKey);

            //  生成刷新Token
            // String refreshToken = UUID.randomUUID().toString().replace("-", "");

            //  放入缓存
            // HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();

            /**
             * 如果可以允许用户退出后token如果在有效期内仍然可以使用的话，那么就不需要存Redis
             * 因为，token要跟用户做关联的话，就必须得每次都带一个用户标识，
             * 那么校验token实际上就变成了校验token和用户标识的关联关系是否正确，且token是否有效
             */
            ///String key = MD5Encoder.encode(userId.getBytes());

            String key = userId;
            // hashOperations.put(key, "token", token);
            // hashOperations.put(key, "refreshToken", refreshToken);
            // stringRedisTemplate.expire(key, 1000, TimeUnit.MILLISECONDS);

            return new DataResponse(token);
        }

        return DataResponse.fail("error");
    }

    /**
     * 89      * 退出
     * 90
     */
    @GetMapping("/logout")
    public DataResponse logout(@RequestParam("userId") String userId) {
        // HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        // String key = userId;
        // hashOperations.delete(key);
        return new DataResponse();
    }

    /**
     * 100      * 刷新Token
     * 101
     */
    @PostMapping("/refreshToken")
    public DataResponse refreshToken() {
        //@RequestBody @Validated RefreshRequest request, BindingResult bindingResult
        // String userId = request.getUserId();
        // String refreshToken = request.getRefreshToken();
        // HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        // String key = userId;
        // String originalRefreshToken = hashOperations.get(key, "refreshToken");
        // if (StringUtils.isBlank(originalRefreshToken) || !originalRefreshToken.equals(refreshToken)) {
        //     return ResponseResult.error(ResponseCodeEnum.REFRESH_TOKEN_INVALID.getCode(), ResponseCodeEnum.REFRESH_TOKEN_INVALID.getMessage());
        // }

        // //  生成新token
        // String newToken = JWTUtil.generateToken(userId, secretKey);
        // hashOperations.put(key, "token", newToken);
        // stringRedisTemplate.expire(userId, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

        return new DataResponse();
    }

}
