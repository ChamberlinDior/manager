package com.schoolmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.schoolmanagement")
public class SchoolmanagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(SchoolmanagementApplication.class, args);
	}
}