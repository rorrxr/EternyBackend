package com.company.eterny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class EternyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EternyBackendApplication.class, args);
		System.out.println("\n" +
			"=================================================\n" +
			"🎮 Eterny - 이터널리턴 전적 검색 서비스 시작 완료! 🎮\n" +
			"📖 Swagger UI: http://localhost:8080/swagger-ui.html\n" +
			"🔍 API Docs: http://localhost:8080/api-docs\n" +
			"💗 Health Check: http://localhost:8080/actuator/health\n" +
			"================================================="
		);
	}
}
