package com.shopupspot.userauthenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopupspot.userauthenticationservice.dto.LoginRequestDto;
import com.shopupspot.userauthenticationservice.security.CustomAuthenticationProvider;
import com.shopupspot.userauthenticationservice.security.JwtResponse;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try {
        	Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());

        	Authentication authenticatedUser = customAuthenticationProvider.authenticate(authentication);

            
        	return ResponseEntity.ok("User logged in successfully");
        } catch (AuthenticationException e) {
            // If authentication fails, return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
