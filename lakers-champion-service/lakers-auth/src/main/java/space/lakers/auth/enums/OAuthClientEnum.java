package space.lakers.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author mini kobe
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum OAuthClientEnum {

    /***/
    TEST("client", "测试客户端"),
    ADMIN("youlai-admin", "系统管理端"),
    WEAPP("youlai-weapp", "微信小程序端");


    @Getter
    private final String clientId;

    @Getter
    private final String desc;

    public static Map<String, OAuthClientEnum> map = Arrays.stream(OAuthClientEnum.values())
            .collect(Collectors.toMap(OAuthClientEnum::getClientId, Function.identity()));


}
