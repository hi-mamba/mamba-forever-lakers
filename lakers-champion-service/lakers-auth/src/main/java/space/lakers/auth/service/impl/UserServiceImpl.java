package space.lakers.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import space.lakers.auth.domain.SecurityUser;
import space.lakers.constant.AuthConstant;
import space.lakers.domain.UserDTO;
import space.lakers.family.service.UserFeignClient;
import space.lakers.portal.service.UmsMemberFeignClient;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    private List<UserDTO> userList;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();
        userList.add(new UserDTO(1L, "macro", password, 1, CollUtil.toList("ADMIN")));
        userList.add(new UserDTO(2L, "andy", password, 1, CollUtil.toList("TEST")));
    }

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