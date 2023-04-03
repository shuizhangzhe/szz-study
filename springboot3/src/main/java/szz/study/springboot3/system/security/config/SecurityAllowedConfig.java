package szz.study.springboot3.system.security.config;

/**
 * @author Administrator
 */
public class SecurityAllowedConfig {

    /**
     * Swagger Allowed
     */
    public static final String[] SWAGGER_ALLOWED = {
            "/doc.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    /**
     * System Allowed
     */
    public static final String[] SYSTEM_ALLOWED = {

    };
}
