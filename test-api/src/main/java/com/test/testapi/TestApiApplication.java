package com.test.testapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@EnableConfigurationProperties
@SpringBootApplication
public class TestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApiApplication.class, args);
	}
}
