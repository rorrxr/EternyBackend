package com.company.eterny.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.company.eterny.domain")
@EnableTransactionManagement
public class JpaConfig {

    // JPA Auditing과 Repository 스캔 활성화
    // @CreatedDate, @LastModifiedDate 등이 자동으로 동작함
}