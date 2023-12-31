package com.shopupspot.userauthenticationservice.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = 0L;
	private String userName;
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Authority> authorities;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile; // Reference to the associated Profile entity

}