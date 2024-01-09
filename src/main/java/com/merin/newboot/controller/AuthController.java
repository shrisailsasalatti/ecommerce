package com.merin.newboot.controller;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.merin.newboot.exception.UserCreationException;
import com.merin.newboot.dto.SignInRequest;
import com.merin.newboot.dto.SignUpRequest;
import com.merin.newboot.entity.User;
import com.merin.newboot.service.UserService;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller",description = "Authentication Management Portal")
public class AuthController {
	
	Logger logger=LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest)throws UserCreationException {
        try {
            User newUser = userService.signUp(signUpRequest.toUser());
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        try {
            String accessToken = userService.signIn(signInRequest.getEmail(), signInRequest.getPassword());
            Claims claims = userService.extractClaims(accessToken);

            SignInResponse response = new SignInResponse(accessToken, claims.getExpiration());
            
            logger.info("LogIn Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    // Create a SignInResponse class for better structure
    private static class SignInResponse {
        private final String accessToken;
        private final Date expiration;

        public SignInResponse(String accessToken, Date expiration) {
            this.accessToken = accessToken;
            this.expiration = expiration;
        }

        // Getters for accessToken and expiration
        public String getAccessToken() {
            return accessToken;
        }

        public Date getExpiration() {
            return expiration;
        }
    }
}
