package szz.study.springboot3.system.security.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import szz.study.springboot3.system.mvc.repository.po.SysRoleMenu;
import szz.study.springboot3.system.mvc.service.SysMenuService;
import szz.study.springboot3.system.mvc.service.SysRoleMenuService;
import szz.study.springboot3.system.mvc.service.SysRoleService;
import szz.study.springboot3.system.security.pojo.SecurityMenu;
import szz.study.springboot3.system.security.pojo.SecurityRole;
import szz.study.springboot3.system.security.service.SecurityService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;


    @Override
    public List<SecurityMenu> securityMenus() {
        // 获取全部的角色信息并与其唯一标识做映射
        final Map<Long, SecurityRole> securityRoles = sysRoleService.list().stream().map(SecurityRole::new).collect(Collectors.toMap(SecurityRole::getId, Function.identity()));

        // 获取角色菜单关联信息
        final List<SysRoleMenu> roleMenus = sysRoleMenuService.list();

        // 菜单唯一标识和角色实体绑定
        final Map<Long, List<SecurityRole>> securityMenuIdSecurityRolesMap = new HashMap<>(8);

        for (SysRoleMenu roleMenu : roleMenus) {
            Long roleId = roleMenu.getRoleId();
            Long menuId = roleMenu.getMenuId();
            if (!securityMenuIdSecurityRolesMap.containsKey(menuId)) {
                securityMenuIdSecurityRolesMap.put(menuId, new ArrayList<>());
            }
            securityMenuIdSecurityRolesMap.get(menuId).add(securityRoles.get(roleId));
        }

        // 获取全部菜单
        return sysMenuService.list().stream().map(o -> {
            List<SecurityRole> roles;
            if (securityMenuIdSecurityRolesMap.containsKey(o.getId())) {
                roles = Optional.ofNullable(securityMenuIdSecurityRolesMap.get(o.getId())).orElse(new ArrayList<>());
            } else {
                roles = new ArrayList<>();
            }
            return new SecurityMenu(o, roles);
        }).toList();
    }
}
