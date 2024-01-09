package com.merin.newboot.service;

import io.jsonwebtoken.*;



import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.merin.newboot.entity.User;
import com.merin.newboot.exception.UserCreationException;
import com.merin.newboot.repository.UserRepository;
import com.merin.newboot.validator.ResourceValidator;

import java.security.Key;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
	Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

	   private final UserRepository userRepository;
	   
	   @Autowired
		private ResourceValidator resourceValidator;
	   
	    private final PasswordEncoder passwordEncoder;
	    
	    

	    private final Key jwtSecretKey; // Use a secure key

	    @Value("${jwt.expiration}")
	    private int jwtExpiration; // Load this from application properties

	    @Value("${jwt.refreshExpiration}")
	    private int jwtRefreshExpiration; // Load this from application properties

	    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	    }

    @Override
    public User signUp(User user)throws UserCreationException  {
    	
    	
    	// Check if the generated username is already taken
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
     	
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email address already in use");
        }
//        if (!resourceValidator.usernameValidator(user.getUsername())) {
//            throw new UserCreationException("Enter Valid User Name");
//        }
        
    	if (user.getPassword() == null || !resourceValidator.passwordValidator(user.getPassword())) {
    	    throw new UserCreationException("Enter The Valid Password Format (With 1-UpperCase, 1-LoweCase, 1-SpecialCharacter, 1-Number with minimum length 5");
    	}

        if (!resourceValidator.contactValidator(user.getPhonenumber())) {
            throw new UserCreationException("Enter The Valid 10 Digit Mobile Number");
        }
        if (!resourceValidator.emailValidator(user.getEmail())) {
            throw new UserCreationException("Enter Valid Email ID");
        }
        
       
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

   
    
    @Override
    public String signIn(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = generateToken(email, jwtExpiration);
        String refreshToken = generateToken(email, jwtRefreshExpiration);

        // You may want to store the refresh token securely (e.g., in a database)

        return accessToken;
    }


    @SuppressWarnings("deprecation")
	@Override
    public Claims extractClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token has expired");
        } catch (JwtException e) {
            throw new RuntimeException("Invalid token");
        }
    }

    @Override
    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String generateToken(String email, int expirationTimeInSeconds) {
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInSeconds * 1000);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(jwtSecretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
