package com.capsulecorp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ServerApplication {

	public static final String[] STANDARD_METHODS = {"GET", "HEAD", "PUT"};
	public static final String[] STANDARD_ORIGIN = {"*"};
	
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() 
	{
		return new WebMvcConfigurer() 
		{
			@Override
			public void addCorsMappings(CorsRegistry registry) 
			{
				registry.addMapping("/**")
					.allowedOrigins(STANDARD_ORIGIN)
					.allowedMethods(STANDARD_METHODS);
			}
		};
	}
	
}
