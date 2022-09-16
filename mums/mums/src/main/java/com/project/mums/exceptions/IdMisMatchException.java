package com.project.mums.exceptions;

@SuppressWarnings("serial")
public class IdMisMatchException extends RuntimeException{
	String urlId;
	String bodyId;
	

	public IdMisMatchException(String urlId, String bodyId) {
		super(String.format("ID in URL '%s' does not match with ID in the request body '%s'", urlId, bodyId));
		this.urlId = urlId;
		this.bodyId = bodyId;
	}


	
	public String getUrlId() {
		return urlId;
	}


	public String getBodyId() {
		return bodyId;
	}

}