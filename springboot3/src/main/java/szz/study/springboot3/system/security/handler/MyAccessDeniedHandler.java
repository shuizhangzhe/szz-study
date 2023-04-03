package szz.study.springboot3.system.security.handler;

import com.cssc.cloud.starter.core.result.RestResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import szz.study.springboot3.system.security.SecurityWriter;
import szz.study.springboot3.system.security.enumeration.SecurityEnum;

import java.io.IOException;

/**
 * @author Administrator
 */
@Component
public class MyAccessDeniedHandler extends SecurityWriter implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        this.write(response, RestResult.fail(SecurityEnum.AUTHORIZATION_FAIL, "权限不足!"));
    }
}
