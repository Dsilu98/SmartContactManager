package com.smart.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.entities.User;
import com.smart.exceptions.EmailAlreadyExistsException;
import com.smart.exceptions.UserNotFoundException;
import com.smart.repositories.UserRepository;
import com.smart.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository repository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.repository = repository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}


	@Override
	public User createUser(User user) throws EmailAlreadyExistsException{
		if (repository.findByEmail(user.getEmail()).isPresent()  ) {
			throw new EmailAlreadyExistsException("Email is already registered !!");
		}
		
		String randomId = UUID.randomUUID().toString();
		
		user.setId(randomId);
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()) );
		
		return repository.save(user);
	}
	

	


	@Override
	public User getUserByEmail(String email) throws UserNotFoundException{
		return repository.findByEmail(email).orElseThrow(()->new UserNotFoundException(email+" - is not a valid userId"));
	}
	
}
