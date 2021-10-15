package space.lakers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mini kobe
 * @date 2021/8/25
 * <pre>
 *
 * </pre>
 */
@AllArgsConstructor
@Getter
public enum CodeEnum {

    /**
     * */
    CLIENT_AUTHENTICATION_FAILED(1001, "客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR(1002, "用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式");

    private final int code;

    private final String desc;
}
