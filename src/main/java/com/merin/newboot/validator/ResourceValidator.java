package com.merin.newboot.validator;

public interface ResourceValidator {
	
	public boolean usernameValidator(String username);
	
	public boolean nameValidator(String name);
	
	public boolean passwordValidator(String password);
	
	public boolean contactValidator(String contact);
	
	public boolean emailValidator(String email);
	
}
