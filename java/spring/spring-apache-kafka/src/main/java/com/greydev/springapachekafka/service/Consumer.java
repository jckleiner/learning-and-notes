package com.greydev.springapachekafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {

	//	@KafkaListener(topics = "mytopic", groupId = "mygroup")
	//	public void consumeFromTopic(String message) {
	//		System.out.println("Consumed message " + message);
	//	}


	// group id?
	@KafkaListener(id = "myId_forKafkaListener", topics = "mytopic")
	public void listen(String message) {
		System.out.println("Consumed message " + message);
	}


	//TODO: This method was triggered multiple times and displayed all the previous messages as well??? Why?
	@KafkaListener(id = "myId_forKafkaListener2", topics = "mytopic")
	public void listen2(ConsumerRecord<String, String> consumerRecord) {
		System.out.println("consumerRecord: " + consumerRecord);
	}
}
