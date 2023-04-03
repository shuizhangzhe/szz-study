package szz.study.springboot3.system.mvc.controller.login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import szz.study.springboot3.system.mvc.repository.form.LoginForm;


/**
 * @author Administrator
 */
@Tag(name = "登录与注销 前端控制器")
@RestController
public class LoginController {

    @PostMapping(value = "/login")
    @Operation(summary = "登录", description = "登录", tags = {"登录与注销 前端控制器"})
    public void login(
            @Validated @RequestBody LoginForm loginForm
    ) {
        /// Security 接管
    }

    @PostMapping(value = "/logout")
    @Operation(summary = "注销", description = "注销", tags = {"登录与注销 前端控制器"})
    public void logout() {
        /// Security 接管
    }
}
