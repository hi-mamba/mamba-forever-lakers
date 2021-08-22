package space.mamba.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import space.lakers.service.UmsMemberFeignClient;
import space.mamba.auth.domain.SecurityUser;
import space.mamba.constant.AuthConstant;
import space.mamba.domain.UserDTO;
import space.mamba.family.service.UserFeignClient;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *      用户管理业务类
 * </pre>
 */
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private UmsMemberFeignClient umsMemberFeignClient;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        UserDTO userDto;
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)) {
            userDto = userFeignClient.loadUserByUsername(username);
        } else {
            userDto = umsMemberFeignClient.loadUserByUsername(username);
        }
        if (userDto == null) {
           // throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDto);
       // if (!securityUser.isEnabled()) {
       //     throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
       // } else if (!securityUser.isAccountNonLocked()) {
       //     throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
       // } else if (!securityUser.isAccountNonExpired()) {
       //     throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
       // } else if (!securityUser.isCredentialsNonExpired()) {
       //     throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
       // }
        return securityUser;
    }

}