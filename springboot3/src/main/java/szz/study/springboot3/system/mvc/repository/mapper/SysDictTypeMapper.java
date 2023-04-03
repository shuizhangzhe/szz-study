package szz.study.springboot3.system.mvc.repository.mapper;

import szz.study.springboot3.system.mvc.repository.po.SysDictType;
import com.cssc.cloud.starter.mybatis.plus.custom.CustomBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统字典类型表 Mapper 接口
 *
 * @author szz
 * @since 2023-03-29
 */
@Mapper
public interface SysDictTypeMapper extends CustomBaseMapper<SysDictType> {

}
