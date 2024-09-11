package com.smart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	private  UserService userService;

	@Autowired
	public HomeController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String showRegistrationrForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUSer(@Valid @ModelAttribute("user")User user,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "register";
		}
		
		try {
			userService.createUser(user);
			model.addAttribute("user", user);
			model.addAttribute("registarionSuccessMessage", "User Registered successfully");
			
			return "register";
		} catch (Exception e) {
			model.addAttribute("exceptionMessage", e.getMessage());
			return "register";
		}
		
	}
	
	@GetMapping("/login")
	public String showLoginPage(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            HttpServletRequest request,
            Model model) {
		if(error != null) {
			String errorMessage = (String) request.getSession().getAttribute("error");
            model.addAttribute("errorMessage", errorMessage != null ? errorMessage : "Invalid email or password.");
            request.getSession().removeAttribute("error");
            
		}
		if (logout != null) {
            model.addAttribute("logoutMessage", "You have been successfully logged out.");
        }
		
		return "login";
	}
	
	
	
	@GetMapping("/home")
	public String showHome() {
		return "home";
	}
	
	
}
