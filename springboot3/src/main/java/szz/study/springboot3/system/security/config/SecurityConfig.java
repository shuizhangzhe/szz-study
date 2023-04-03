package szz.study.springboot3.system.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import szz.study.springboot3.system.security.constant.SecurityConstant;
import szz.study.springboot3.system.security.filter.MyAuthorizationManager;
import szz.study.springboot3.system.security.filter.MyUsernamePasswordAuthenticationFilter;
import szz.study.springboot3.system.security.handler.MyAccessDeniedHandler;
import szz.study.springboot3.system.security.handler.MyAuthenticationEntryPoint;
import szz.study.springboot3.system.security.handler.MyLogoutSuccessHandler;
import szz.study.springboot3.system.security.service.impl.MyUserDetailsServiceImpl;
import szz.study.springboot3.system.security.strategy.MyInvalidSessionStrategy;
import szz.study.springboot3.system.security.strategy.MySessionInformationExpiredStrategy;

/**
 * @author Administrator
 */
@Configuration
public class SecurityConfig {

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     *
     * @param authenticationConfiguration 身份验证配置
     * @return 身份验证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 注册身份验证提供程序
     *
     * @param myUserDetailsService 用户信息服务实现
     * @return 身份验证提供程序
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(MyUserDetailsServiceImpl myUserDetailsService) {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setUserDetailsPasswordService(myUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            MyAuthorizationManager myAuthorizationManager,
            MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter,
            MyLogoutSuccessHandler myLogoutSuccessHandler,
            MyInvalidSessionStrategy myInvalidSessionStrategy,
            MySessionInformationExpiredStrategy mySessionInformationExpiredStrategy,
            MyAuthenticationEntryPoint myAuthenticationEntryPoint,
            MyAccessDeniedHandler myAccessDeniedHandler
    ) throws Exception {
        // 开启跨域资源共享
        http.cors();

        // 关闭CSRF跨站保护
        http.csrf().disable();

        // 授权跳过
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, SecurityAllowedConfig.SWAGGER_ALLOWED).permitAll()
                .requestMatchers(HttpMethod.GET, SecurityAllowedConfig.SYSTEM_ALLOWED).permitAll()
                .anyRequest().access(myAuthorizationManager)
        ;

        // 登录过滤器
        http.addFilterAt(myUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 用户登出配置 (成功处理)
        http.logout().logoutSuccessHandler(myLogoutSuccessHandler).clearAuthentication(true).deleteCookies(SecurityConstant.COOKIE_NAME);

        // Session
        http.sessionManagement()
                // 会话过期处理
                .invalidSessionStrategy(myInvalidSessionStrategy)
                // 设置最大允许登录数
                .maximumSessions(1)
                // 当达到最大允许登录数后是否阻止登录
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(mySessionInformationExpiredStrategy);

        // 指定认证错误处理器
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler);

        return http.build();
    }
}
