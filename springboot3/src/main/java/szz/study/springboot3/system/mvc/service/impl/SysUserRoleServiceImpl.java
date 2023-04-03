package szz.study.springboot3.system.mvc.service.impl;

import szz.study.springboot3.system.mvc.repository.mapper.SysUserRoleMapper;
import szz.study.springboot3.system.mvc.repository.po.SysUserRole;
import szz.study.springboot3.system.mvc.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统用户角色关联表 服务实现类
 *
 * @author szz
 * @since 2023-03-29
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
