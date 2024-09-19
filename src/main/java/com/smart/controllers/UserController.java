package com.smart.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.repositories.ContactRepository;
import com.smart.services.ContactService;
import com.smart.services.UserService;
import com.smart.util.FileUploadUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	private ContactService contactService;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	public UserController(UserService userService, ContactService contactService) {
		super();
		this.userService = userService;
		this.contactService = contactService;
	}


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
		
		model.addAttribute("title", "User dashboard");
		model.addAttribute("user", user);
		return "user/user_dashboard";
	}
	
	//show or view contact form
	@GetMapping("/addContact")
	public String showContactForm(Model model) {
		model.addAttribute("title", "contact_form");
		model.addAttribute("contact", new Contact());
		
		return "user/contact_form";
	}
	
	//registering or saving contact details
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute(value = "contact")Contact contact,
			@RequestParam("imageFile")MultipartFile imageFile,
			Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		User user = userService.getUserByEmail(userId);
		
		
		try {
			String imageUrl = FileUploadUtil.saveFile(imageFile);
			contact.setImage(imageUrl);
			
			contact.setUser(user);
			contactService.saveContact(contact);
			
			
			
			model.addAttribute("contact", contact);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "user/contact-form";
		}
		
		return "redirect:/user/addContact";
	}
	
	//geting all contacts by user
	@GetMapping("/show-contacts")
	public String showContacts(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		User user = userService.getUserByEmail(userId);
		
		model.addAttribute("contact", contactRepository.findAll());
		System.out.println(contactRepository.findAll());
		
		return "user/view_contact";
	}
	
}
