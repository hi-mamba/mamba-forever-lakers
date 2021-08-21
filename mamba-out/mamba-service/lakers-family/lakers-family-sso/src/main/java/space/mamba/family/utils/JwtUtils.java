package space.mamba.family.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *  JWT工具类
 * </pre>
 */
@Slf4j
public class JwtUtils {

    public static final long TOKEN_EXPIRE_TIME = 7200 * 1000;

    private static final String ISSUER = "lakers";

    /**
     * 生成Token
     *
     * @param username  用户标识（不一定是用户名，有可能是用户ID或者手机号什么的）
     * @param secretKey
     * @return
     */
    public static String generateToken(String username, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(expireTime)
                .withClaim("username", username)
                .sign(algorithm);

        return token;
    }


    /**
     * 46      * 校验Token
     * 47      * @param token
     * 48      * @param secretKey
     * 49      * @return
     * 50
     */
    public static void verifyToken(String token, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        jwtVerifier.verify(token);
    }

    /**
     * 68      * 从Token中提取用户信息
     * 69      * @param token
     * 70      * @return
     * 71
     */


    public static String getUserInfo(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getClaim("username").asString();
        return username;

    }
}
