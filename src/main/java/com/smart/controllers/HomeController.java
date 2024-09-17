package com.smart.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smart.entities.User;
import com.smart.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HomeController {
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	public HomeController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/register")
	public String showRegistrationrForm(Model model) {
		model.addAttribute("user", new User());
		logger.info("Redirectin to registration page");
		return "register";
	}

	@PostMapping("/register")
	public String registerUSer(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model ,
			@RequestParam(value = "termsAccepted", defaultValue = "false") boolean termsAccepted) {
		// it checks validation error
		if (bindingResult.hasErrors()) {
			logger.error("validation errors");
			return "register";
		}

		// it checks if agreement is accepted or not
		if (!termsAccepted) {
			logger.error("terms and conditions not accepeted");
			model.addAttribute("termsAccepted", "Read the terms and condition and accept it");
			return "register";
		}

		try {
			userService.createUser(user);
			model.addAttribute("user", user);
			model.addAttribute("registarionSuccessMessage", "User Registered successfully");

			logger.error("Successfull Registration");
			return "register";
		} catch (Exception e) {
			model.addAttribute("exceptionMessage", e.getMessage());
			
			logger.info(e.getMessage());
			return "register";
		}

	}

	@GetMapping("/login")
	public String showLoginPage() {

		return "loginpage";
	}

	@GetMapping("/home")
	public String showHome(Model model) {

		return "homepage";
	}

}
