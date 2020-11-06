package com.stacksimplify.restservices.springbootbuildingblocks.exceptions;

import java.util.Date;

public class CustomErrorDetails{
	
	//Creates simple custom error details bean
	private Date timestamp;
	private String message;
	private String errorDetails;
	
	//Fields constructor
	public CustomErrorDetails(Date timestamp, String message, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}
	//Getters
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	
	
	
}
