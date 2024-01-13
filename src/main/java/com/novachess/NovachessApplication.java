package com.novachess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-secret.properties")
public class NovachessApplication {

	public static void main(String[] args) {
		SpringApplication.run(NovachessApplication.class, args);
	}

}
