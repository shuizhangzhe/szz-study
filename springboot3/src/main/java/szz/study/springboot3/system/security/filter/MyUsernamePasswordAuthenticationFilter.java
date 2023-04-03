package szz.study.springboot3.system.security.filter;

import com.alibaba.fastjson2.JSON;
import com.cssc.cloud.starter.core.exception.BusinessException;
import com.cssc.cloud.starter.core.result.RestResultEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import szz.study.springboot3.system.security.handler.MyAuthenticationFailureHandler;
import szz.study.springboot3.system.security.handler.MyAuthenticationSuccessHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;


/**
 * @author Administrator
 */
@Component
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public MyUsernamePasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            MyAuthenticationSuccessHandler myAuthenticationSuccessHandler,
            MyAuthenticationFailureHandler myAuthenticationFailureHandler
    ) {
        super(authenticationManager);
        super.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        super.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        super.setFilterProcessesUrl("/login");
    }

    private static boolean isContentTypeJson(HttpServletRequest request) {
        return MimeTypeUtils.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username;
        String password;
        if (isContentTypeJson(request)) {
            try (InputStream is = request.getInputStream()) {
                Map<String, String> authenticationBean = JSON.parseObject(is, Map.class);
                username = authenticationBean.get("username");
                password = authenticationBean.get("password");
            } catch (IOException e) {
                throw new BusinessException(RestResultEnum.FAIL, e);
            }
        } else {
            username = obtainUsername(request);
            password = obtainPassword(request);
        }
        username = Optional.ofNullable(username).orElse("");
        password = Optional.ofNullable(password).orElse("");
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
