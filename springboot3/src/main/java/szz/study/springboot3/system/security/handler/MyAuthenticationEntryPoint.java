package szz.study.springboot3.system.security.handler;

import com.cssc.cloud.starter.core.result.RestResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import szz.study.springboot3.system.security.SecurityWriter;
import szz.study.springboot3.system.security.enumeration.SecurityEnum;

import java.io.IOException;

/**
 * @author Administrator
 */
@Component
public class MyAuthenticationEntryPoint extends SecurityWriter implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        this.write(response, RestResult.fail(SecurityEnum.AUTHENTICATION_FAIL, "未登录!"));
    }
}
