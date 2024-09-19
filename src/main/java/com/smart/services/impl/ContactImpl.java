package com.smart.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.entities.Contact;
import com.smart.repositories.ContactRepository;
import com.smart.services.ContactService;

@Service
public class ContactImpl implements ContactService{
	
	private ContactRepository contactRepository;
	
	@Autowired
	public ContactImpl(ContactRepository contactRepository) {
		super();
		this.contactRepository = contactRepository;
	}



	@Override
	public Contact saveContact(Contact contact) {
		String randomId = UUID.randomUUID().toString();
		
		contact.setId(randomId);
		
		return contactRepository.save(contact);
	}

}
