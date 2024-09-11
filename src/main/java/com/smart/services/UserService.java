package com.smart.services;

import java.util.Optional;

import com.smart.entities.User;

public interface UserService {
	//create user
	User createUser(User user);
	
	User getUserByEmail(String email);
}
