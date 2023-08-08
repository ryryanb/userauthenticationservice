package com.shopupspot.userauthenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopupspot.userauthenticationservice.models.Profile;
import com.shopupspot.userauthenticationservice.security.SecurityUser;
import com.shopupspot.userauthenticationservice.services.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/me")
    public ResponseEntity<Profile> getCurrentUserProfile(Authentication authentication) {
        Long currentUserId = getCurrentUserId(authentication);
        Profile profile = profileService.getUserProfile(currentUserId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/me")
    public ResponseEntity<String> updateCurrentUserProfile(Authentication authentication, @RequestBody Profile updatedProfile) {
        Long currentUserId = getCurrentUserId(authentication);
        profileService.updateUserProfile(currentUserId, updatedProfile);
        return ResponseEntity.ok("Profile updated successfully.");
    }

    private Long getCurrentUserId(Authentication authentication) {
    	SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        // Assuming your UserDetails contains the user's ID
        return userDetails.getId();
    }
}
