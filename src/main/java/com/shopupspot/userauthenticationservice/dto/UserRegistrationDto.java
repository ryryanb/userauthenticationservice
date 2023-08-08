package com.shopupspot.userauthenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
	private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String address;

}
