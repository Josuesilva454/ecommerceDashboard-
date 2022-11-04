package com.tutorial.demo.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EcommerceApplication {


	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
