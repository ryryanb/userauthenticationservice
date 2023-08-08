package com.shopupspot.userauthenticationservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopupspot.userauthenticationservice.dto.UserRegistrationDto;
import com.shopupspot.userauthenticationservice.dto.UserResponseDto;
import com.shopupspot.userauthenticationservice.models.Profile;
import com.shopupspot.userauthenticationservice.models.User;
import com.shopupspot.userauthenticationservice.repositories.ProfileRepository;
import com.shopupspot.userauthenticationservice.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProfileRepository profileRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;;

    public void registerUser(User user) {
        // Add any additional validation or processing here before saving the user
        userRepository.save(user);
    }
    
    public User getUserById(Long id) {
    	
    	Optional<User> userOptional = userRepository.findById(id);
    	User user = userOptional.orElse(null);
        // Add any additional validation or processing here before saving the user
        return user;
    }
    
    public UserResponseDto registerNewUser(UserRegistrationDto registrationDto) { 	
    	String hashedPassword = passwordEncoder.encode(registrationDto.getPassword());
    	User newUser = new User(
            registrationDto.getUserName(),
            hashedPassword
        );

        // Save the user along with the associated UserRole
        newUser = userRepository.save(newUser);
        
        Profile newProfile = new Profile();
        newProfile.setAddress(registrationDto.getAddress());
        newProfile.setEmail(registrationDto.getEmail());
        newProfile.setFirstName(registrationDto.getFirstName());
        newProfile.setLastName(registrationDto.getLastName());
     newProfile.setUser(newUser); // Set the user for the profile
     profileRepository.save(newProfile);

        // Create the UserResponseDto to return
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(newUser.getId());
		
        // Get the roles from the user and set them in the response DTO
       /* List<String> roles = newUser.getUserRoles().stream()
            .map(userRole -> userRole.getRole().getName().name())
            .collect(Collectors.toList());
        responseDto.setRoles(roles);*/

        return responseDto;
    }
}
