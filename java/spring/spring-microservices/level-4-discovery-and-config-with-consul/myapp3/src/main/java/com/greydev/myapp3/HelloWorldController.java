package com.greydev.myapp3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

	@GetMapping("/hello")
	public String helloWorld() {
		return "hello world from myapp 3";
	}
}
