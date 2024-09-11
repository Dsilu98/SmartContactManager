package com.smart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandaler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobaaleException(Exception ex,Model model){
		model.addAttribute("errorMessage","Internal Error occured !!");
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex,Model model){
		model.addAttribute("errorMessage", ex.getMessage());
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String> handleEmailAlreadyExist(EmailAlreadyExistsException ex,Model model){
		model.addAttribute("errorMessage", ex.getMessage());
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
