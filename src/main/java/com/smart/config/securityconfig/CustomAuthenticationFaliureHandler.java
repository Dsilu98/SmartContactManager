package com.smart.config.securityconfig;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomAuthenticationFaliureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		
		if (exception.getMessage().equals("User not found")) {
            response.sendRedirect("/login?useriderror=true");
        }
		else if (exception.getMessage().equals("Bad credentials")) {
            response.sendRedirect("/login?passworderror=true");
        } else {
            response.sendRedirect("/login?error=true");
        }
		
		
	}

}
