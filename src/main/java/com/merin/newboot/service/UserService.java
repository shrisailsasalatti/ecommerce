package com.merin.newboot.service;

import com.merin.newboot.entity.User;
import com.merin.newboot.exception.UserCreationException;

import io.jsonwebtoken.Claims;

public interface UserService {

	public User signUp(User user)throws UserCreationException ;

    String signIn(String username, String password);

    Claims extractClaims(String token);

    boolean validateToken(String token);
}
