package com.company.eterny.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Swagger/OpenAPI 설정
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Eterny API")
                .description("이터널리턴 전적 검색 서비스 API 문서")
                .version("v1.0")
                .contact(new Contact()
                    .name("Eterny Team")
                    .email("contact@eterny.com")
                    .url("https://eterny.com"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8080")
                    .description("개발 서버"),
                new Server()
                    .url("https://api.eterny.com")
                    .description("운영 서버")
            ));
    }
}
