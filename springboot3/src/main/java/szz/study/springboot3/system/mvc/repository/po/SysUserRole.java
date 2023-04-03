package szz.study.springboot3.system.mvc.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.io.Serial;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 系统用户角色关联表
 *
 * @author szz
 * @since 2023-03-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @Schema(description = "用户Id")
    private Long userId;

    @Schema(description = "角色Id")
    private Long roleId;


}
