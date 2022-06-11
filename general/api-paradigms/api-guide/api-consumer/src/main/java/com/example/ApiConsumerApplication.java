package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients // NEEDED - else the generated apiClient cannot be autowired
// @EnableFeignClients("com.example.api.companies") -> if the package where MyServiceApplication exist is not a "parent"
// package of the generated api client class
public class ApiConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiConsumerApplication.class, args);
	}

}
