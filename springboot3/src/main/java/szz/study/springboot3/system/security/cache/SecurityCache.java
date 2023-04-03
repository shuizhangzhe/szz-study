package szz.study.springboot3.system.security.cache;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateUnit;
import org.springframework.stereotype.Component;
import szz.study.springboot3.system.security.pojo.SecurityMenu;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class SecurityCache {

    final public Cache<Integer, List<SecurityMenu>> securityMenuCache = CacheUtil.newFIFOCache(1, DateUnit.SECOND.getMillis() * 60);

}
