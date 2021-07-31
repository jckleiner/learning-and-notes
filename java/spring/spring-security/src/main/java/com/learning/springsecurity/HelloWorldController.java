package com.learning.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

	@GetMapping("/")
	public String hello() {
		return "Hello <h1>World!</h1>";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Hello <h1>ADMIN!</h1>";
	}

	@GetMapping("/user")
	public String user() {
		return "Hello <h1>USER!</h1>";
	}

}
