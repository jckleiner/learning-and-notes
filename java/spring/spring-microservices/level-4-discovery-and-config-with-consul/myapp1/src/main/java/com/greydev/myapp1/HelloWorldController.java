package com.greydev.myapp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RefreshScope // this is needed so @Value("${app.externalProp1}") updates dynamically
public class HelloWorldController {

	@Autowired
	private RestTemplate restTemplate;

	// comes from consul
	@Value("${app.externalProp1}")
	private String externalProp1;

	// You can also dynamically get the environment property from consul
	// The changes are reflected immediately since env.getProperty will be called each time and fetches the newest value.
	// AFAIK these values could be created in consul by hand or with a REST api
	@Autowired
	private Environment env;

	@GetMapping("/hello")
	public String helloWorld() {
		return String.format("hello world from myapp 1. <br>" +
				"app.dynamicProperty: %s <br>" +
				"app.externalProperty1: %s",
				env.getProperty("app.dynamicProperty"), externalProp1);
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

}
