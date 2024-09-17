package com.smart.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	private String id;
	
	@NotEmpty(message = "Please Enter your name")
	private String name;
	
	@NotEmpty(message="enter your email")
	@Column(unique = true)
	private String email;
	
	private String role;
	
	private boolean enabled;
	
	@Size(min = 8, message = "Password must be at least 8 characters long.")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter.")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter.")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit.")
    @Pattern(regexp = ".*[@#$%^&+=!].*", message = "Password must contain at least one special character.")
	private String password;
	
	private String imageUrl;
	
	@Column(length = 500)
	private String about;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Contact> contacts = new ArrayList<>();
	
	
	public User() {
		super();
	}


	public User(String id, @NotEmpty(message = "Please Enter your name") String name,
			@NotEmpty(message = "enter your email") String email, String role, boolean enabled,
			@Size(min = 8, message = "Password must be at least 8 characters long.") @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter.") @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter.") @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit.") @Pattern(regexp = ".*[@#$%^&+=!].*", message = "Password must contain at least one special character.") String password,
			String imageUrl, String about, List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.enabled = enabled;
		this.password = password;
		this.imageUrl = imageUrl;
		this.about = about;
		this.contacts = contacts;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public List<Contact> getContacts() {
		return contacts;
	}


	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	
	
}
