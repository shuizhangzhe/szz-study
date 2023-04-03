package szz.study.springboot3.system.security.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import szz.study.springboot3.system.mvc.repository.po.SysUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SecurityUser extends SysUser implements UserDetails {

    private List<SecurityRole> roles;

    private List<SecurityMenu> menus;

    @JSONField(serialize = false)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(roles).orElse(new ArrayList<>());
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return this.getIsAccountNonExpired();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return this.getIsAccountNonLocked();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return this.getIsCredentialsNonExpired();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return this.getIsEnabled();
    }

    public SecurityUser(SysUser sysUser, List<SecurityRole> roles) {
        // 赋值给父类
        BeanUtils.copyProperties(sysUser, this);
        // 赋值给角色
        this.roles = roles;
    }
}
