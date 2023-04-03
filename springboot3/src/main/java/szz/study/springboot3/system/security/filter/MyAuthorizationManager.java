package szz.study.springboot3.system.security.filter;

import jakarta.annotation.Resource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import szz.study.springboot3.system.security.cache.SecurityCache;
import szz.study.springboot3.system.security.pojo.SecurityMenu;
import szz.study.springboot3.system.security.pojo.SecurityRole;
import szz.study.springboot3.system.security.service.SecurityService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Administrator
 */
@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Resource
    private SecurityService securityService;

    @Resource
    private SecurityCache securityCache;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        // ServletPath
        String servletPath = context.getRequest().getServletPath();
        // 获取菜单所有可访问角色
        Collection<ConfigAttribute> configAttributes = getAttributes(servletPath);
        // 获取当前角色
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.get().getAuthorities();
        for (ConfigAttribute configAttribute : configAttributes) {
            for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                if (configAttribute.getAttribute().equals(grantedAuthority.getAuthority())) {
                    return new AuthorizationDecision(true);
                }
            }
        }
        return new AuthorizationDecision(false);
    }

    public Collection<ConfigAttribute> getAttributes(String servletPath) throws IllegalArgumentException {
        // 校验并更新缓存
        if (securityCache.securityMenuCache.get(0, false) == null) {
            securityCache.securityMenuCache.put(0, securityService.securityMenus());
        }
        List<SecurityMenu> securityMenus = securityCache.securityMenuCache.get(0);
        Collection<ConfigAttribute> configAttributes = SecurityConfig.createList();
        for (SecurityMenu securityMenu : securityMenus) {
            if (antPathMatcher.match(securityMenu.getPath(), servletPath)) {
                List<SecurityRole> securityRoles = securityMenu.getRoles();
                String[] roleArr = new String[securityRoles.size()];
                for (int i = 0; i < roleArr.length; i++) {
                    roleArr[i] = securityRoles.get(i).getTag();
                }
                configAttributes.addAll(SecurityConfig.createList(roleArr));
            }
        }
        if (configAttributes.isEmpty()) {
            configAttributes.addAll(SecurityConfig.createList("ROLE_LOGIN"));
        }
        return new HashSet<>(configAttributes);
    }
}
