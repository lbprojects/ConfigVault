package com.app.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("Spring boot Application")
			.description("Backend APIs for config vault app")
			.version("v1.0.0")
			.license(new License().name("License").url("/")))
			.externalDocs(new ExternalDocumentation().description("Config Vault Documentation")
			.url("http://localhost:8080/swagger-ui/index.html"));
	}
	
}
