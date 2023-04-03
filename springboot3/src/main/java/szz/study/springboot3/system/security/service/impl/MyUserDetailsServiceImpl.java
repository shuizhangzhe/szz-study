package szz.study.springboot3.system.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import szz.study.springboot3.system.mvc.repository.po.*;
import szz.study.springboot3.system.mvc.service.*;
import szz.study.springboot3.system.security.pojo.SecurityRole;
import szz.study.springboot3.system.security.pojo.SecurityUser;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService, UserDetailsPasswordService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysUserRoleService sysUserRoleService;


    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        final SysUser sysUser = sysUserService.getSysUserByUsername(user.getUsername());
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        sysUser.setPassword(newPassword);
        sysUserService.updateById(sysUser);
        List<SecurityRole> securityRoles = userRoles(sysUser.getId());
        return new SecurityUser(sysUser, securityRoles);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final SysUser sysUser = sysUserService.getSysUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        List<SecurityRole> securityRoles = userRoles(sysUser.getId());
        return new SecurityUser(sysUser, securityRoles);
    }

    /**
     * 根据用户唯一标识获取用户角色唯一标识列表
     *
     * @param userId 用户唯一标识
     * @return 角色唯一标识列表
     */
    private List<Long> userRoleIds(Long userId) {
        final LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        return sysUserRoleService.list(wrapper).stream().map(SysUserRole::getRoleId).toList();
    }

    /**
     * 根据用户唯一标识获取用户角色列表
     *
     * @param userId 用户唯一标识
     * @return Security 安全框架所需的角色实体列表
     */
    private List<SecurityRole> userRoles(Long userId) {
        final LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysRole::getId, userRoleIds(userId));
        return sysRoleService.list(wrapper).stream().map(SecurityRole::new).toList();
    }
}
