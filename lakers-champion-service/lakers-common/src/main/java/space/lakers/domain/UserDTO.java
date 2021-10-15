package space.lakers.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author mini kobe
 * @date 2021/8/21
 * <pre>
 *  登录用户信息
 * </pre>
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private Integer status;

    private String clientId;

    private List<String> roles;

    public UserDTO(Long id, String username, String password, Integer status,List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }
}
