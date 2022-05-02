package com.example.java_project_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class JavaProjectGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectGatewayApplication.class, args);
	}

}
