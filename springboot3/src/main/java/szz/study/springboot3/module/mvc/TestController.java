package szz.study.springboot3.module.mvc;

import com.cssc.cloud.starter.core.result.RestResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import szz.study.springboot3.system.security.cache.SecurityCache;
import szz.study.springboot3.system.security.service.SecurityService;

/**
 * @author Administrator
 */
@Tag(name = "测试 前端控制器")
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private SecurityCache securityCache;

    @Resource
    private SecurityService securityService;

    @GetMapping(value = "/1/{id}")
    @Operation(summary = "测试1", description = "测试1", tags = {"测试 前端控制器"})
    public RestResult<Void> test1(
            @PathVariable String id,
            @RequestParam String username
    ) {
        return RestResult.success();
    }

    @PostMapping(value = "/refreshSecurityMenu")
    @Operation(summary = "立即刷新菜单缓存", description = "立即刷新菜单缓存", tags = {"测试 前端控制器"})
    public RestResult<Void> refreshSecurityMenu() {
        securityCache.securityMenuCache.put(0, securityService.securityMenus());
        return RestResult.success();
    }
}
