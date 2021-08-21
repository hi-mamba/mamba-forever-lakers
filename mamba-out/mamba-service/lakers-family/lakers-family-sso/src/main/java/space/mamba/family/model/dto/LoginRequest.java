package space.mamba.family.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
@Getter
@Setter
@ToString
public class LoginRequest {

    private String username;

    private String password;
}
