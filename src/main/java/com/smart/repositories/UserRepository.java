package com.smart.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
	Optional<User> findByEmail(String email);
}
