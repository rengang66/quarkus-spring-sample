package com.iiit.spring.sample.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iiit.spring.sample.kafka.producer.ProjectProducer;
import com.iiit.spring.sample.kafka.domain.ResponseDto;
import com.iiit.spring.sample.kafka.domain.Project;

@RestController
public class ProjectController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private ProjectProducer kafkaProducerService;
	
	@PostMapping(value = "projects/postMessage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseDto postMessage(@RequestBody Project project) throws Exception {
		logger.info("Entering ProjectController.postMessage() with Project :: {}", project);
		
		kafkaProducerService.sendMessage(project);		
		
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Game Over for " + project.toString());
		
		logger.info("Leaving ProjectController.postMessage() with responseDto:: {}", responseDto);	
		return responseDto;		
	}

}
