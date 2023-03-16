package com.iiit.spring.sample.kafka.domain;

public class ResponseDto {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseDto [message=" + message + "]";
	}

}
