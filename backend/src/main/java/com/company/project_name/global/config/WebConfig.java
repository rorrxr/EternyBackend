package com.company.project_name.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry reg) {
        reg.addMapping("/api/bser/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET");
    }
}