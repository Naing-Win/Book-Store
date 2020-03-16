package com.nw.spring.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleResourceNotFoundException(Exception exception) {
		return "error/404";
	}

}
