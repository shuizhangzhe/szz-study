package szz.study.springboot3.system.security.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import szz.study.springboot3.system.mvc.repository.po.SysMenu;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SecurityMenu extends SysMenu {

    private List<SecurityRole> roles;

    public SecurityMenu(SysMenu sysMenu, List<SecurityRole> roles) {

        BeanUtils.copyProperties(sysMenu, this);

        this.roles = roles;
    }
}
