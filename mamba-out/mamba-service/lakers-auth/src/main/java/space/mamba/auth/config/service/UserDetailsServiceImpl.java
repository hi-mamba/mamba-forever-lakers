package space.mamba.auth.config.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.mamba.family.service.UserFeignClient;

/**
 * 【重要】从数据库获取用户信息，用于和前端传过来的用户信息进行密码判读
 *
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

}
