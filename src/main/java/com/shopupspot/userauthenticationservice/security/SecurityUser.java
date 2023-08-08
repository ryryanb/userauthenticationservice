package com.shopupspot.userauthenticationservice.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shopupspot.userauthenticationservice.models.User;

public class SecurityUser implements UserDetails {
	
	private static final long serialVersionUID = -8133709890175514557L;
	private final User user;
	public SecurityUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getUser().getAuthorities().stream()
				.map(a -> new SimpleGrantedAuthority(
						a.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return getUser().getPassword();
	}

	@Override
	public String getUsername() {
		return getUser().getUserName();
	}
	
	public Long getId() {
		return getUser().getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private User getUser() {
		return user;
	}
	

}
