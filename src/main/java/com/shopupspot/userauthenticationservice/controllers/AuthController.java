package com.shopupspot.userauthenticationservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopupspot.userauthenticationservice.dto.LoginRequestDto;
import com.shopupspot.userauthenticationservice.security.CustomAuthenticationProvider;
import com.shopupspot.userauthenticationservice.security.JwtResponse;
import com.shopupspot.userauthenticationservice.security.JwtTokenProvider;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
	    try {
	        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());

	        Authentication authenticatedUser = customAuthenticationProvider.authenticate(authentication);
	        
	        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	        securityContext.setAuthentication(authenticatedUser);
	        SecurityContextHolder.setContext(securityContext);
	        
	        

	        Object principal = authenticatedUser.getPrincipal();
	        logger.info(principal.toString());

	            String jwt = jwtTokenProvider.generateToken(authenticatedUser);

	            // Create a response object containing the JWT token
	            JwtResponse response = new JwtResponse(jwt);

	            return ResponseEntity.ok(response);

	    } catch (AuthenticationException e) {
	        // If authentication fails, return an error response
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }
	}

	@GetMapping("/ciao")
	public String ciao() {
		return "Ciao!";
	}
}
