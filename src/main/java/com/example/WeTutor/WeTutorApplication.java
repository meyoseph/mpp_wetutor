package com.example.WeTutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WeTutorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeTutorApplication.class, args);
	}

}
