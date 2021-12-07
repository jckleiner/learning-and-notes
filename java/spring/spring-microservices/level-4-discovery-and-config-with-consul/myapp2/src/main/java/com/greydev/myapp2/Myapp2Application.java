package com.greydev.myapp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class Myapp2Application {

	public static void main(String[] args) {
		SpringApplication.run(Myapp2Application.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate loadbalancedRestTemplate() {
		return new RestTemplate();
	}

}
