package szz.study.springboot3.system.security.handler;

import com.cssc.cloud.starter.core.result.RestResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import szz.study.springboot3.system.security.SecurityWriter;
import szz.study.springboot3.system.security.enumeration.SecurityEnum;

import java.io.IOException;

/**
 * @author Administrator
 */
@Component
public class MyAuthenticationFailureHandler extends SecurityWriter implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        var msg = "登录失败";
        if (exception instanceof InternalAuthenticationServiceException || exception instanceof UsernameNotFoundException) {
            msg = "账号不存在!";
        } else if (exception instanceof DisabledException) {
            msg = "账号不可用!";
        } else if (exception instanceof AccountExpiredException) {
            msg = "账号已过期!";
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "密码已过期!";
        } else if (exception instanceof LockedException) {
            msg = "账号已冻结!";
        } else if (exception instanceof BadCredentialsException) {
            msg = "用户名或密码错误!";
        } else {
            msg = "登录失败!";
        }
        this.write(response, RestResult.fail(SecurityEnum.AUTHENTICATION_FAIL, msg));
    }
}
