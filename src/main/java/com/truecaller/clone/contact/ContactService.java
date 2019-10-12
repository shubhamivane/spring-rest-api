package com.truecaller.clone.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truecaller.clone.contact.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public Contact getContact(long id) {
		Optional<Contact> contact = contactRepository.findById(id);
		return contact.orElse(null);
	}
	
	public boolean createContact(Contact contact) {
		try {
			contactRepository.save(contact);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public boolean deleteContact(long id) {
		try {
			contactRepository.deleteById(id);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public boolean updateContact(Contact contact) {
		try {
			contactRepository.save(contact);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public List<Contact> getContactListOfUser(String userId) {
		List<Contact> userContactList = new ArrayList<>();
		contactRepository.findByUserId(userId).forEach(userContactList::add);
		return userContactList;
	}
	
	public Contact searchPhoneNo(String phoneNo) {
		Contact contact = contactRepository.findByphoneNo(phoneNo);
		return contact;
	}
	
	public Contact searchPhoneNoInUserContactList(String id, String phoneNo) {
		Contact contact = contactRepository.findByUserIdAndPhoneNo(id, phoneNo);
		return contact;
	}
}
