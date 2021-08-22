package com.greydev.springapachekafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.greydev.springapachekafka.service.Producer;


@RestController
public class Controller {

	@Autowired
	private Producer producer;


	@GetMapping("/publishToTopic")
	public void sendMessage(@RequestParam String message) {
		producer.publishToTopic(message);
	}

	@GetMapping("/produceRandomMessage")
	public void produceRandomMessage() {
		Faker faker = new Faker();
		String message = faker.chuckNorris().fact();
		producer.publishToTopic(message);
	}
}
