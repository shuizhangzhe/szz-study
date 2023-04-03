package szz.study.springboot3.system.mvc.service.impl;

import szz.study.springboot3.system.mvc.repository.mapper.SysRoleMapper;
import szz.study.springboot3.system.mvc.repository.po.SysRole;
import szz.study.springboot3.system.mvc.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统角色表 服务实现类
 *
 * @author szz
 * @since 2023-03-29
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}
