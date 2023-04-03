package szz.study.springboot3.system.security.strategy;

import com.cssc.cloud.starter.core.result.RestResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;
import szz.study.springboot3.system.security.SecurityWriter;
import szz.study.springboot3.system.security.enumeration.SecurityEnum;

import java.io.IOException;

/**
 * @author Administrator
 */
@Component
public class MyInvalidSessionStrategy extends SecurityWriter implements InvalidSessionStrategy {

    private boolean createNewSession = true;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (this.createNewSession) {
            request.getSession();
        }
        this.write(response, RestResult.fail(SecurityEnum.AUTHENTICATION_FAIL, "会话超时!"));
    }

    /**
     * Determines whether a new session should be created before redirecting (to avoid
     * possible looping issues where the same session ID is sent with the redirected
     * request). Alternatively, ensure that the configured URL does not pass through the
     * {@code SessionManagementFilter}.
     * @param createNewSession defaults to {@code true}.
     */
    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}
