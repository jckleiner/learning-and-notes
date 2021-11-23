package com.greydev.springbootconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {

	@Value("${app.fullName}")
	private String name;

	@GetMapping("/greeting")
	public String greeting() {
		return "Hello " + name;
	}
}

