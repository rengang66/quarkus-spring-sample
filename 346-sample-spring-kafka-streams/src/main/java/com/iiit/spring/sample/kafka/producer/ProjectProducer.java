package com.iiit.spring.sample.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.iiit.spring.sample.kafka.domain.Project;

@Service
public class ProjectProducer {

	private static final Logger logger = LoggerFactory.getLogger(ProjectProducer.class);

	private KafkaTemplate<String, Project> kafkaTemplate;
	
	@Value("#{kafkaConfig.getStreamInputTopicName()}")
	private String streamInputTopicName;
	
	@Autowired
	ProjectProducer(KafkaTemplate<String, Project> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(Project project) {

		ListenableFuture<SendResult<String, Project>> future = kafkaTemplate.send(streamInputTopicName, String.valueOf(project.id), project);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Project>>() {

			@Override
			public void onSuccess(SendResult<String, Project> result) {
				logger.info("Sent message=[" + project + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.info("Unable to send message=[" + project + "] due to : " + ex.getMessage());
			}
		});
	}

}
