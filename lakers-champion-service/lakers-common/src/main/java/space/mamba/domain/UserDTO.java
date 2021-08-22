package space.mamba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *  登录用户信息
 * </pre>
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private Integer status;

    private String clientId;

    private List<String> roles;
}
