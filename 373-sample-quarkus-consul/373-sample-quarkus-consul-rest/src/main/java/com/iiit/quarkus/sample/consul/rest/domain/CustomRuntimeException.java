package com.iiit.quarkus.sample.consul.rest.domain;

public class CustomRuntimeException extends RuntimeException {
	public CustomRuntimeException(String message) {
		super(message);
	}
}
