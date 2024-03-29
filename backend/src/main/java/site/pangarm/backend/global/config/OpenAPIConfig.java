package site.pangarm.backend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@OpenAPIDefinition(
        info =
        @Info(
                title = "Pangarm API Document",
                description = "Pangarm 프로젝트의 API 명세서",
                version = "v1"))
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        String schema = "Authorization";
        SpringDocUtils.getConfig().addAnnotationsToIgnore(AuthenticationPrincipal.class);
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Pangarm 프로젝트 API")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(schema))
                .components(new Components()
                        .addSecuritySchemes(schema,
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .name(schema)
                                        .scheme("Bearer")
                                        .bearerFormat(schema)));

    }
}
