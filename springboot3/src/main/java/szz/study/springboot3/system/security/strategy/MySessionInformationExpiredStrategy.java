package szz.study.springboot3.system.security.strategy;


import com.cssc.cloud.starter.core.result.RestResult;
import jakarta.servlet.ServletException;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import szz.study.springboot3.system.security.SecurityWriter;
import szz.study.springboot3.system.security.enumeration.SecurityEnum;

import java.io.IOException;

/**
 * @author Administrator
 */
@Component
public class MySessionInformationExpiredStrategy extends SecurityWriter implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        this.write(event.getResponse(), RestResult.fail(SecurityEnum.AUTHENTICATION_FAIL, "您的账号再另一处登录!"));
    }
}
