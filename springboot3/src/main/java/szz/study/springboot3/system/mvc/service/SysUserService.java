package szz.study.springboot3.system.mvc.service;

import szz.study.springboot3.system.mvc.repository.po.SysUser;
import com.cssc.cloud.starter.mybatis.plus.custom.CustomService;

/**
 * 系统用户表 服务类
 *
 * @author szz
 * @since 2023-03-29
 */
public interface SysUserService extends CustomService<SysUser> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser getSysUserByUsername(String username);

}
