package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;




@SpringBootApplication
@RequestMapping
public class MySpringBootApplication {
	
	public static void main(String[] args) {

		// code run java plication
		SpringApplication.run(MySpringBootApplication.class, args);
		
	}
	
}
