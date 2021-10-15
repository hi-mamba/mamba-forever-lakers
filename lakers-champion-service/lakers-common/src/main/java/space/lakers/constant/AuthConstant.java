package space.lakers.constant;

/**
 * @author mini kobe
 * @date 2021/8/22
 * <pre>
 *  权限相关常量定义
 * </pre>
 */
public class AuthConstant {

    /**
     * JWT存储权限前缀
     */
    public static String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    public static String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 后台管理client_id
     */
    public static String ADMIN_CLIENT_ID = "admin-app";

    /**
     * 前台商城client_id
     */
    public static  String PORTAL_CLIENT_ID = "portal-app";

    /**
     * 后台管理接口路径匹配
     */
    public static  String ADMIN_URL_PATTERN = "/mall-admin/**";

    /**
     * Redis缓存权限规则key
     */
    public static String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 认证信息Http请求头
     */
    public static String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    public static String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    public static String USER_TOKEN_HEADER = "user";
}
