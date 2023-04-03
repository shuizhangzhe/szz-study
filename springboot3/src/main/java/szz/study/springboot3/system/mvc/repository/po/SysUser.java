package szz.study.springboot3.system.mvc.repository.po;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.io.Serial;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 系统用户表
 *
 * @author szz
 * @since 2023-03-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @JSONField(serialize = false)
    @Schema(description = "帐户未过期")
    private Boolean isAccountNonExpired;

    @JSONField(serialize = false)
    @Schema(description = "账户未上锁")
    private Boolean isAccountNonLocked;

    @JSONField(serialize = false)
    @Schema(description = "凭据未过期")
    private Boolean isCredentialsNonExpired;

    @JSONField(serialize = false)
    @Schema(description = "允许使用")
    private Boolean isEnabled;

    @JSONField(serialize = false)
    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JSONField(serialize = false)
    @Schema(description = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
