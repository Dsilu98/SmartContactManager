package com.smart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	//to add common data to the ui thouth modele attribute
	@ModelAttribute
	public void addCommonData(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		User user = userService.getUserByEmail(userId);
		
		model.addAttribute("user", user);
	}
	
	
	//home dashboard
	@GetMapping("/index")
	public String usreIndex(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		User user = userService.getUserByEmail(userId);
		
		model.addAttribute("user", user);
		return "user/user_dashboard";
	}
	
	//show or view contact form
	@GetMapping("/addContact")
	public String showContactForm(Model model) {
		model.addAttribute("contact", new Contact());
		
		return "user/contact_form";
	}
	
	//registering or saving contact details
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute(value = "contact")Contact contact, Model model) {
		
		model.addAttribute("contact", contact);
		return "normal/addContact";
	}
}
