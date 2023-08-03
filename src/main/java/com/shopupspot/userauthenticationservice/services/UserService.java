package com.shopupspot.userauthenticationservice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopupspot.userauthenticationservice.dto.UserRegistrationDto;
import com.shopupspot.userauthenticationservice.dto.UserResponseDto;
import com.shopupspot.userauthenticationservice.models.User;
import com.shopupspot.userauthenticationservice.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
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

        // Create the UserResponseDto to return
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(newUser.getId());
		/*
		 * responseDto.setFirstName(newUser.getFirstName());
		 * responseDto.setLastName(newUser.getLastName());
		 * responseDto.setEmail(newUser.getEmail());
		 */

        // Get the roles from the user and set them in the response DTO
       /* List<String> roles = newUser.getUserRoles().stream()
            .map(userRole -> userRole.getRole().getName().name())
            .collect(Collectors.toList());
        responseDto.setRoles(roles);*/

        return responseDto;
    }
}
