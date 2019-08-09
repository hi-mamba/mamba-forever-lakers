package space.mamba.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfo {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机
     */
    private String mobile;

    /**
     * 注册时间
     */
    private LocalDateTime regTime;

    /**
     * 注册IP
     */
    private Long regIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP
     */
    private Long lastLoginIp;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 用户状态
     */
    private Byte status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}