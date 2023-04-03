package szz.study.springboot3.system.security.handler;

import com.cssc.cloud.starter.core.result.RestResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import szz.study.springboot3.system.security.SecurityWriter;

import java.io.IOException;

/**
 * @author Administrator
 */
@Component
public class MyLogoutSuccessHandler extends SecurityWriter implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        this.write(response, RestResult.success("注销成功!"));
    }
}
