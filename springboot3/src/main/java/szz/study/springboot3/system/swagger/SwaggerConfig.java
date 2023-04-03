package szz.study.springboot3.system.swagger;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
public class SwaggerConfig {

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    @Bean
    public Info openApiInfo(SwaggerProperties swaggerProperties) {
        return new Info()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .license(
                        new License()
                                .name(swaggerProperties.getLicense())
                                .url(swaggerProperties.getLicenseUrl())
                ).contact(
                        new Contact()
                                .name(swaggerProperties.getContact().getName())
                                .email(swaggerProperties.getContact().getEmail())
                                .url(swaggerProperties.getContact().getUrl())
                ).termsOfService(swaggerProperties.getTermsOfServiceUrl());
    }

    @Bean
    public GroupedOpenApi defaultGroup(Info openApiInfo) {
        return GroupedOpenApi.builder()
                .group("default")
                .addOpenApiCustomizer(openApi -> {
                    openApiInfo.setTitle("全部");
                    openApi.info(openApiInfo);
                })
                .build();
    }

    @Bean
    public GroupedOpenApi systemGroup(Info openApiInfo) {
        return GroupedOpenApi.builder()
                .group("system")
                .addOpenApiCustomizer(openApi -> {
                    openApiInfo.setTitle("系统模块");
                    openApi.info(openApiInfo);
                })
                .packagesToScan("szz.study.springboot3.system.mvc.controller")
                .build();
    }
}
