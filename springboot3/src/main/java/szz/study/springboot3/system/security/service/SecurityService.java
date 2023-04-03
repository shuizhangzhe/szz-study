package szz.study.springboot3.system.security.service;

import szz.study.springboot3.system.security.pojo.SecurityMenu;

import java.util.List;

/**
 * @author Administrator
 */
public interface SecurityService {

    /**
     * 获取菜单资源
     *
     * @return 菜单资源列表
     */
    List<SecurityMenu> securityMenus();

}
