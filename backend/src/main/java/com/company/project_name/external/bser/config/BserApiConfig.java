package com.company.project_name.external.bser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BserApiConfig {

    @Value("${bser.api.key:}")
    private String apiKey;

    @Value("${bser.api.base-url:https://open-api.bser.io}")
    private String baseUrl;

    @Bean("bserRestTemplate")
    public RestTemplate bserRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("x-api-key", apiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }

    @Bean("bserBaseUrl")
    public String bserBaseUrl() {
        return baseUrl;
    }
}
