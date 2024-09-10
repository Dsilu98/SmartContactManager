package com.smart.services;

import com.smart.entities.User;

public interface UserService {
	//create user
	User createUser(User user);
	
	User getUserByEmail(String email);
}
