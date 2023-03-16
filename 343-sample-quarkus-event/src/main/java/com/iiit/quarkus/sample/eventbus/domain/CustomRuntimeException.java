package com.iiit.quarkus.sample.eventbus.domain;

public class CustomRuntimeException extends RuntimeException {
	public CustomRuntimeException(String message) {
		super(message);
	}
}
