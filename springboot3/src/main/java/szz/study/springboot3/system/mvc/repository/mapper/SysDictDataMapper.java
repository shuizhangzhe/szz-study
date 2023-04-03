package szz.study.springboot3.system.mvc.repository.mapper;

import szz.study.springboot3.system.mvc.repository.po.SysDictData;
import com.cssc.cloud.starter.mybatis.plus.custom.CustomBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统字典数据表 Mapper 接口
 *
 * @author szz
 * @since 2023-03-29
 */
@Mapper
public interface SysDictDataMapper extends CustomBaseMapper<SysDictData> {

}
