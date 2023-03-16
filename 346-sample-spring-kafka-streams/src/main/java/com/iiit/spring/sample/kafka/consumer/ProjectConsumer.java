package com.iiit.spring.sample.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.iiit.spring.sample.kafka.domain.Project;

@Service
public class ProjectConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectConsumer.class);

	@KafkaListener(topics = "#{kafkaConfig.getStreamOutputTopicName()}")
	public void listen(@Payload Project project, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		logger.info("Received Message: " + project + " from partition: " + partition);
		System.out.println("Received Message: " + project + " from partition: " + partition);
	}
}
