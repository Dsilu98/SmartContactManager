package com.smart.config.securityconfig;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.entities.User;
import com.smart.repositories.UserRepository;
import com.smart.services.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private  UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
		 
	        
	        return new org.springframework.security.core.userdetails.User(user.getEmail(),
	        		user.getPassword(),
	                Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
	}
	
	public void checkPassword(UserDetails user, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new UsernameNotFoundException("Bad credentials");
        }
    }

}
