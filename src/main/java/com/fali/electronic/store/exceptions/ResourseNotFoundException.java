package com.fali.electronic.store.exceptions;

import lombok.Builder;

@Builder
public class ResourseNotFoundException extends RuntimeException {

	
	public ResourseNotFoundException() {
		super("Resource not found");
	}
	
	public ResourseNotFoundException(String message) {
		super(message);
	}
}
