package szz.study.springboot3.system.security.enumeration;

import com.cssc.cloud.starter.core.result.StatusBaseEnum;
import lombok.Getter;

/**
 * @author Administrator
 */
@Getter
public enum SecurityEnum implements StatusBaseEnum {

    // 认证异常
    AUTHENTICATION_FAIL(401, "认证异常!"),
    // 授权异常
    AUTHORIZATION_FAIL(403, "授权异常!"),

    ;

    SecurityEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
