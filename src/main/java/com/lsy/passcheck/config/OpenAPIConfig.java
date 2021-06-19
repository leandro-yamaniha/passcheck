package com.lsy.passcheck.config;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
public class OpenAPIConfig {


	@Bean
	public io.swagger.v3.oas.models.OpenAPI customOpenAPI(
			@Value("${info.build.version}") final String appVersion,
			@Value("${info.build.name}") final String title,
			@Value("${info.build.description}") final String description) {
		return new io.swagger.v3.oas.models.OpenAPI()
				.info(
					new Info().title(title)
					.description(description)
					.version(appVersion));
	}


}
