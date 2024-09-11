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
		String errorMessage = "Invalid email or password.";
		
		if(exception.getMessage().equals("User is disabled")) {
			errorMessage = "Your account has been disabled. Please contact support.";
		}
		else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
			errorMessage = "Your account has expired. Please contact support.";
		}
		else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "No account found with the provided email.";
		}
		
		
		request.getSession().setAttribute("error", errorMessage);
		response.sendRedirect("/login?error=true");
	}

}
