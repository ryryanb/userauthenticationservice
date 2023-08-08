package com.shopupspot.userauthenticationservice.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopupspot.userauthenticationservice.models.Profile;
import com.shopupspot.userauthenticationservice.repositories.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile getUserProfile(Long userId) {
        Optional<Profile> optionalProfile = profileRepository.findByUserId(userId);
        return optionalProfile.orElse(null);
    }

    public void updateUserProfile(Long userId, Profile updatedProfile) {
    	Optional<Profile> optionalExistingProfile = profileRepository.findByUserId(userId);
    	
    	if (optionalExistingProfile.isPresent()) {
            Profile existingProfile = optionalExistingProfile.get();
            
            // Update properties of existingProfile with values from updatedProfile
            existingProfile.setFirstName(updatedProfile.getFirstName());
            existingProfile.setLastName(updatedProfile.getLastName());
            // ... Update other properties
            
            // Save the updated profile back to the repository
            profileRepository.save(existingProfile);
        } else {
            // Handle the case when no profile exists for the given userId
            throw new NoSuchElementException("Profile not found for user ID: " + userId);
        }
    }
}
