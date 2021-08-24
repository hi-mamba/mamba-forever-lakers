package space.lakers.auth.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */
@Data
@Builder
public class Oauth2TokenDTO {
    //@ApiModelProperty("访问令牌")
    private String token;
    //@ApiModelProperty("刷令牌")
    private String refreshToken;
    //@ApiModelProperty("访问令牌头前缀")
    private String tokenHead;
    //@ApiModelProperty("有效时间（秒）")
    private int expiresIn;
}