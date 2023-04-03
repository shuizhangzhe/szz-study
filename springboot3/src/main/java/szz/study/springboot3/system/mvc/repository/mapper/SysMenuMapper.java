package szz.study.springboot3.system.mvc.repository.mapper;

import szz.study.springboot3.system.mvc.repository.po.SysMenu;
import com.cssc.cloud.starter.mybatis.plus.custom.CustomBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统菜单表 Mapper 接口
 *
 * @author szz
 * @since 2023-03-29
 */
@Mapper
public interface SysMenuMapper extends CustomBaseMapper<SysMenu> {

}
