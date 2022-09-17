package com.project.mums.exceptions;

@SuppressWarnings("serial")
public class EmpDetailNotFoundException extends RuntimeException{
	String resourceName;
	String fieldName;
	String fieldValue;
	
	
	public EmpDetailNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with %s = %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	
}
