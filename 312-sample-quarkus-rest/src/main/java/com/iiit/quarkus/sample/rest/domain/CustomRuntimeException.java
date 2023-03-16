package com.iiit.quarkus.sample.rest.domain;

public class CustomRuntimeException extends RuntimeException {
	public CustomRuntimeException(String message) {
		super(message);
	}
}
