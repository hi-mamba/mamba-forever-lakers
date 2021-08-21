package space.mamba.auth.domain;
import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import space.mamba.family.entity.AuthMemberDTO;
import space.mamba.family.entity.SysUser;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author pankui
 * @date 2021/8/21
 * <pre>
 *
 * </pre>
 */
/**
 * 登录用户信息
 */
@Data
@NoArgsConstructor
public class OAuthUserDetails implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String clientId;

    private Collection<SimpleGrantedAuthority> authorities;

    public OAuthUserDetails(SysUser user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword("test" + user.getPassword());
        this.setEnabled(user.getStatus() != null);
        if (CollectionUtil.isNotEmpty(user.getRoles())) {
            authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }
    }

    public OAuthUserDetails(AuthMemberDTO member) {
        this.setId(member.getId());
        this.setUsername(member.getUsername());
        this.setPassword("test" + member.getPassword());
        this.setEnabled(member.getStatus() != null);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
