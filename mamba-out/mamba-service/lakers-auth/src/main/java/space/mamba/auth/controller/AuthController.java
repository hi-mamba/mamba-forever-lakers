package space.mamba.auth.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.mamba.family.req.AuthRequest;
import space.mamba.family.resp.AuthResponse;
import space.mamba.auth.service.AuthService;

import javax.annotation.Resource;

/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.register(authRequest));
    }

}
