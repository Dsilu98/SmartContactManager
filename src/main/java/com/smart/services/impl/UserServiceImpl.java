package com.smart.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.entities.User;
import com.smart.repositories.UserRepository;
import com.smart.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository repository;
	
	@Autowired
	public UserServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public User createUser(User user) {
		
		
		String randomId = UUID.randomUUID().toString();
		
		user.setId(randomId);
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		
		return repository.save(user);
	}
	

	@Override
	public User getUserByEmail(String email) {
		return null;
	}

	
}
