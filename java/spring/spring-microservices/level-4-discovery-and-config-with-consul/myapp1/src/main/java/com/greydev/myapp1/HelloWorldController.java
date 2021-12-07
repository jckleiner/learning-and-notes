package com.greydev.myapp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RefreshScope
public class HelloWorldController {

	@Autowired
	private RestTemplate restTemplate;

	// comes from consul
	@Value("${app.externalProp1}")
	private String externalProp1;

	@GetMapping("/hello")
	public String helloWorld() {
		return "hello world from myapp 1";
	}


	@GetMapping("/callMyapp2")
	public String callMyapp2() {
		return "Response from myapp2: "
				+ this.restTemplate.getForObject("http://myapp2/hello", String.class);
	}

	@GetMapping("/callMyapp3")
	public String callMyapp3() {
		return "Response from myapp3: "
				+ this.restTemplate.getForObject("http://myapp3/hello", String.class);
	}

	@GetMapping("/external")
	public String external() {
		return "Value of external prop: " + externalProp1;
	}

}
