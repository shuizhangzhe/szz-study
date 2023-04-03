package szz.study.springboot3.system.security;

import com.alibaba.fastjson2.JSONObject;
import com.cssc.cloud.starter.core.result.RestResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * @author Administrator
 */
public class SecurityWriter {

    protected void write(HttpServletResponse httpServletResponse, RestResult<?> restResult) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.getWriter().write(JSONObject.toJSONString(restResult));
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}
