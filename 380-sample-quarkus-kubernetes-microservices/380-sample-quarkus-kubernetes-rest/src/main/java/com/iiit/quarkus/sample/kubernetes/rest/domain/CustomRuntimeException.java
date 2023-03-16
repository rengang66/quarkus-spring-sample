package com.iiit.quarkus.sample.kubernetes.rest.domain;

public class CustomRuntimeException extends RuntimeException {
	public CustomRuntimeException(String message) {
		super(message);
	}
}
