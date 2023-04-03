package szz.study.springboot3.system.security.handler;

import com.cssc.cloud.starter.core.result.RestResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import szz.study.springboot3.system.security.SecurityWriter;
import szz.study.springboot3.system.security.pojo.SecurityUser;

import java.io.IOException;

/**
 * @author Administrator
 * @link <a href="https://yangruoyu.blog.csdn.net/article/details/128276473">SecurityContext设置参考</a>
 */
@Component
public class MyAuthenticationSuccessHandler extends SecurityWriter implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var user = (SecurityUser) authentication.getPrincipal();
        // 屏蔽密码
        user.setPassword(null);
        // SecurityContext在设置Authentication的时候并不会自动写入Session，读的时候却会根据Session判断，所以需要手动写入一次，否则下一次刷新时SecurityContext是新创建的实例。
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        // 输出信息
        this.write(response, RestResult.success(user));
    }
}
