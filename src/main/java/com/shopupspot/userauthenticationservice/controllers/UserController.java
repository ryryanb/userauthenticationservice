package com.shopupspot.userauthenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopupspot.userauthenticationservice.dto.UserRegistrationDto;
import com.shopupspot.userauthenticationservice.models.User;
import com.shopupspot.userauthenticationservice.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        // Convert the DTO to a User entity
        // Register the user
        userService.registerNewUser(userRegistrationDto);

        return ResponseEntity.ok("User registered successfully");
    }
}
