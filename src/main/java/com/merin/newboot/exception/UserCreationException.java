package com.merin.newboot.exception;

@SuppressWarnings("serial")
public class UserCreationException extends Exception
{

	public String message;
	
	public UserCreationException(String message) 
	{
		super(message);
	}
	
	
	@Override
	public String getMessage() 
	{
		return super.getMessage();
	}
	
	
}
