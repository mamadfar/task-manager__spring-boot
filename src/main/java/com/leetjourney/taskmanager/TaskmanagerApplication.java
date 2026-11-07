package com.leetjourney.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @Configuration --> Spring Boot application is configured using a Java configuration class.
// @EnableAutoConfiguration ---> Spring Boot automatically configures the application based on the classpath and other factors.
// @ComponentScan ---> Spring Boot scans for components, configurations, and services in the current package and its sub-packages.
public class TaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

}
