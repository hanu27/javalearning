package com.javalearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@ComponentScan("com.javalearning")
public class JavalearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavalearningApplication.class, args);
	}

}
