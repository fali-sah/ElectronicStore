package com.fali.electronic.store.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fali.electronic.store.dtos.ApiResponseMessage;

@RestControllerAdvice
public class GlobalExceptionalHandler {

	private Logger logger=LoggerFactory.getLogger(GlobalExceptionalHandler.class);
	
	//handle Resource not found exception
	
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourseNotFoundException ex){
		
		logger.info("Exception Handler invoke!!");
	ApiResponseMessage response = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
	
	return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	//methodArgumentNotValidException
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>>handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
	
		Map<String,Object> response = new HashMap<String,Object>();
		
		allErrors.stream().forEach(objectError ->{
			
			String message = objectError.getDefaultMessage();
			String field = ((FieldError)objectError).getField();
			
			response.put(field, message);
		});
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	
	}
	
}
