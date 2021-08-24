package space.lakers.auth.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.lakers.portal.service.UmsMemberFeignClient;
import space.lakers.auth.domain.SecurityUser;
import space.lakers.constant.AuthConstant;
import space.lakers.domain.UserDTO;
import space.lakers.family.service.UserFeignClient;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *      用户管理业务类
 * </pre>
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private UmsMemberFeignClient umsMemberFeignClient;

    @Resource
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