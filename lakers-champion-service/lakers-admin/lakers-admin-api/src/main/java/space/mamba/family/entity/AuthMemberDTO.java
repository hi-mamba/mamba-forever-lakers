package space.mamba.family.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
@Getter
@Setter
public class AuthMemberDTO {

    private Long id;
    private String username;
    private String password;
    private Integer status;

    private String avatar;
    private String nickname;
}
