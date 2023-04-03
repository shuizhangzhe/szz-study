package szz.study.springboot3.system.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import szz.study.springboot3.system.security.pojo.SecurityRole;
import szz.study.springboot3.system.security.pojo.SecurityUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class SecurityUtils {

    public static SecurityUser getCurrentSecurityUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (SecurityUser) authentication.getPrincipal();
        }
        return null;
    }

    public static String getCurrentSecurityUsername() {
        SecurityUser securityUser = getCurrentSecurityUser();
        if (securityUser != null) {
            return securityUser.getUsername();
        }
        return null;
    }

    public static List<Long> getCurrentSecurityUserRoles() {
        SecurityUser securityUser = getCurrentSecurityUser();
        if (securityUser != null) {
            return securityUser.getRoles().stream().map(SecurityRole::getId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
