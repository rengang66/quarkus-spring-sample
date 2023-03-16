package com.iiit.quarkus.sample.restclient.domain;

public class CustomRuntimeException extends RuntimeException {
	public CustomRuntimeException(String message) {
		super(message);
	}
}
