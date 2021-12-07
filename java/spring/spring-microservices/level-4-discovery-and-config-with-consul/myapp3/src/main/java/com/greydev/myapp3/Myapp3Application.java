package com.greydev.myapp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class Myapp3Application {

	public static void main(String[] args) {
		SpringApplication.run(Myapp3Application.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate loadbalancedRestTemplate() {
		return new RestTemplate();
	}

}
