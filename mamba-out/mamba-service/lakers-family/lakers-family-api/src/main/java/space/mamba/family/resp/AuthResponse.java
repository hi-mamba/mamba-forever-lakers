package space.mamba.family.resp;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
}
