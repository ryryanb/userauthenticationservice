package com.shopupspot.userauthenticationservice.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shopupspot.userauthenticationservice.models.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User save(User user);
	
	Optional<User> findByUserName(String userName);
	
	Optional<User> findById(Long id);


}
