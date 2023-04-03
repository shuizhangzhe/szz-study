package szz.study.springboot3.system.security.constant;

/**
 * @author Administrator
 */
public class SecurityConstant {

    /// DEFAULT ROLE

    /**
     * 管理员角色
     */
    public static String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * 已经登录的默认角色（未分配的角色）
     */
    public static String ROLE_LOGIN = "ROLE_LOGIN";

    /**
     * 未登录的默认角色（匿名角色）
     */
    public static String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    /// COOKIE

    /**
     * COOKIE 名称
     */
    public static String COOKIE_NAME = "JSESSIONID";
}
