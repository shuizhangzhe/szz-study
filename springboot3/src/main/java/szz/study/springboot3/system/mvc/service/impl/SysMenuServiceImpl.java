package szz.study.springboot3.system.mvc.service.impl;

import szz.study.springboot3.system.mvc.repository.mapper.SysMenuMapper;
import szz.study.springboot3.system.mvc.repository.po.SysMenu;
import szz.study.springboot3.system.mvc.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统菜单表 服务实现类
 *
 * @author szz
 * @since 2023-03-29
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

}
