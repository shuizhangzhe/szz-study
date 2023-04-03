package szz.study.springboot3.system.mvc.repository.po;

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
 * 系统菜单表
 *
 * @author szz
 * @since 2023-03-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class SysMenu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "父Id")
    private Long parentId;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "菜单类型（0目录 1菜单 2按钮）")
    private Integer type;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "权限标识")
    private String perms;

    @Schema(description = "排序")
    private Long sort;

    @Schema(description = "状态（0正常 1停用）")
    private Boolean status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
