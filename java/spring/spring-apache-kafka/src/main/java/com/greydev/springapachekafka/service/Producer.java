package com.greydev.springapachekafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class Producer {
	public static final String topic = "mytopic";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void publishToTopic(String message) {
//		ProducerRecord<>
		System.out.println("Publishing to topic " + message);
		// asd
		kafkaTemplate.send(topic, message);
	}
}
