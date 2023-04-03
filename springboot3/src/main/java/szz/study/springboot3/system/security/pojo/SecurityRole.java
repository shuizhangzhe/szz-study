package szz.study.springboot3.system.security.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import szz.study.springboot3.system.mvc.repository.po.SysRole;

import java.util.Optional;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SecurityRole extends SysRole implements GrantedAuthority {

    @JSONField(serialize = false)
    @Override
    public String getAuthority() {
        return Optional.ofNullable(getTag()).orElse("ROLE_DEFAULT");
    }

    public SecurityRole(SysRole sysRole){
        BeanUtils.copyProperties(sysRole, this);
    }
}
