package space.mamba.family.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mamba
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}