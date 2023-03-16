package com.iiit.spring.sample.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelConfig {
	
	@Bean
	public MessageChannel greetingChannel() {
		return MessageChannels.flux("greeting").get();
	}

	@Bean
	public MessageChannel blockingGreetingChannel() {
		return MessageChannels.direct("blocking-greeting").get();
	}
	
	/*
	@Bean
	public MessageChannel listChannel() {
		return MessageChannels.flux("list").get();
	}	
	*/
}
