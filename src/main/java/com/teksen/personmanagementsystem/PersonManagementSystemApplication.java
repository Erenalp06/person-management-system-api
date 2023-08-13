package com.teksen.personmanagementsystem;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
@SecurityScheme(name = "personmsapi", scheme = "basic", description = "Basic Authentication", type = SecuritySchemeType.HTTP)
public class PersonManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonManagementSystemApplication.class, args);
	}



}
