package com.project.mums.exceptions;

@SuppressWarnings("serial")
public class PrimaryKeyViolationException extends RuntimeException{
	String resourceName;
	String id;
	
	
	public PrimaryKeyViolationException(String resourceName, String id) {
		super(String.format("%s with Id = %s already exist. Insert Failed", resourceName, id));
		this.resourceName = resourceName;
		this.id=id;
	}

	public String getResourceName() {
		return resourceName;
	}
	
	public String getId() {
		return id;
	}
}

