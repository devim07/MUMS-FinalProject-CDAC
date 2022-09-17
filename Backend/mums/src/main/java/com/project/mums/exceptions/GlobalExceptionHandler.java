package com.project.mums.exceptions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		Map<String, String> resp=new HashMap<>();
		String fieldName="Resource Unavailable";
		String message=ex.getMessage();
		resp.put(fieldName, message);
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex ) {
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<Map<String, String>> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex){
//		Map<String, String> resp=new HashMap<>();
//		String fieldName="Data Exist";
//		String message="Part or whole of sent data, already exist. Cannot insert into database";
//		resp.put(fieldName, message);
//		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.NOT_ACCEPTABLE);
//	}
	
	@ExceptionHandler(IdMisMatchException.class)
	public ResponseEntity<Map<String, String>> idMisMatchExceptionHandler(IdMisMatchException ex){
		Map<String, String> resp=new HashMap<>();
		String fieldName="Request ID's mis-match";
		String message=ex.getMessage();
		resp.put(fieldName, message);
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Map<String, String>> sQLExceptionHandler(SQLException ex){
		Map<String, String> resp=new HashMap<>();
		String fieldName="Data Rejected by Data Base";
		ex.forEach((error)->{
			String message=error.getMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, String>> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex){
		Map<String, String> resp=new HashMap<>();
		String fieldName="Http Request date not readable";
		String message=ex.getMessage();
		resp.put(fieldName, message);
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.NOT_ACCEPTABLE);
	}
	
}