package space.lakers.family.resp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserVO {


    private String id;
    private String email;
    private String password;
    private String role;

}
