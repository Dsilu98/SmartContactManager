package com.smart.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandaler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobaaleException(Exception ex,Model model){
		return null;
	}
}
