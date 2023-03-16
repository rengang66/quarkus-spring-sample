package com.iiit.spring.sample.streams.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.iiit.spring.sample.streams.service.InMemoryChannel;


@Configuration
public class InMemoryChannelConfig {
	@Bean
	public InMemoryChannel<String> inMemoryChannel() {
		return new InMemoryChannel<>();
	}
}
