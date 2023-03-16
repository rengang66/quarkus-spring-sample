package com.iiit.spring.sample.reactive.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
			.info(getInfo());
	}

	private Info getInfo() {
		return new Info()
			.title("Projects API")
			.version("1.0.0")
			.description("Example Project Service - Spring MVC")
			.license(
				new License()
					.name("Apache 2.0")
					.url("https://www.apache.org/licenses/LICENSE-2.0.html")
			)
			.contact(
				new Contact()
					.name("rengang")
					.email("rengang66@sina.com")
					.url("https://developers.redhat.com/blog/author/edeandrea")
			);
	}
}
