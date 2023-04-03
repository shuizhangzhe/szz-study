package szz.study.springboot3.system.mvc.repository.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
@Schema(name = "登录表单")
public class LoginForm {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;
}
