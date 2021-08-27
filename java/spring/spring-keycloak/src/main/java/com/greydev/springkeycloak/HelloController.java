package com.greydev.springkeycloak;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

	@GetMapping("/hello")
	public ResponseEntity<String> hello(Authentication authentication) {
		final String body = "Hello " + authentication.getName();
		return ResponseEntity.ok(body);
	}


	@GetMapping("/test")
	public String test() {
		return "TEST";
	}


	@GetMapping("/public")
	public String public1() {
		return "PUBLIC";
	}

}

