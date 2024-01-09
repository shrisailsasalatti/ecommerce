package com.merin.newboot.validator;

import org.springframework.stereotype.Component;

@Component
public class ResourceValidatorImpl implements ResourceValidator {
	
	@Override
	public boolean usernameValidator(String username)   //Regex Regular Expressions
	{
		
		return username.matches("^[A-Za-z0-9]{4,15}$");
	}

	@Override
	public boolean nameValidator(String name) 
	{
		
		return name.length()>=4;
	}

	@Override
	public boolean passwordValidator(String password)
	{
		
		return password.matches("^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{5,}$");
	}

	@Override
	public boolean contactValidator(String contact) 
	{
		
		return contact.matches("[0-9]{10}");
	}

	@Override
	public boolean emailValidator(String email)  
	{
		
		return email.matches("^[a-z0-9+_.-]+@[a-zA-Z0-9.-]+$");
	}
	
}
