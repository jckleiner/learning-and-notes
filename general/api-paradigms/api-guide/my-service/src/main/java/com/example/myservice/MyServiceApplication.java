package com.example.myservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients("com.example.api.companies") // NEEDED - else the generated apiClient cannot be autowired
public class MyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyServiceApplication.class, args);
	}

}
