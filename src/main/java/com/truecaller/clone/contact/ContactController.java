package com.truecaller.clone.contact;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.truecaller.clone.parser.PayloadVerifier;
import com.truecaller.clone.parser.ResponseParser;
import com.truecaller.clone.user.UserService;

@RestController
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/user/{userId}/contact", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getContacts(@PathVariable String userId, @RequestBody Map<String, String> payload){
		String[] requiredParam = {"accessToken"};
		PayloadVerifier payloadVerifier = new PayloadVerifier(payload, requiredParam);
		if(!payloadVerifier.isValid()) {
			ResponseParser response = new ResponseParser("Parameters insufficient for operation.", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
		}		
		if(userService.authorizeUser(userId, payload.get("accessToken"))) {
			List<Contact> contactListOfUser = contactService.getContactListOfUser(userId);
			return new ResponseEntity<Object>(contactListOfUser, HttpStatus.OK);
		}
		else {
			ResponseParser response = new ResponseParser("User authorization failed", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping(path="/user/{userId}/contact", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addContact(@PathVariable String userId, @RequestBody Map<String, String> payload){
		String[] requiredParam = {"name", "phoneNo", "accessToken"};
		PayloadVerifier payloadVerifier = new PayloadVerifier(payload, requiredParam);
		if(!payloadVerifier.isValid()) {
			ResponseParser response = new ResponseParser("Parameters insufficient for operation.", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		if(userService.authorizeUser(userId, payload.get("accessToken"))) {
			Contact contact = new Contact(payload.get("name"), payload.get("phoneNo"), userId);
			boolean status = contactService.createContact(contact);
			if(status) {
				return new ResponseEntity<Object>(contact, HttpStatus.CREATED);
			}
			else {
				ResponseParser response = new ResponseParser("Couldn't add contact. Try again later.", "FAILED");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
		}
		else {
			ResponseParser response = new ResponseParser("User authentication failed.", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@DeleteMapping(path="/user/{userId}/contact/{contactId}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteContact(@PathVariable String userId, @PathVariable long contactId, @RequestBody Map<String, String> payload){
		String[] requiredParam = {"accessToken"};
		PayloadVerifier payloadVerifier = new PayloadVerifier(payload, requiredParam);
		if(!payloadVerifier.isValid()) {
			ResponseParser response = new ResponseParser("Parameters insufficient for operation.", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		ResponseParser response = new ResponseParser();
		if(userService.authorizeUser(userId, payload.get("accessToken"))) {
			Contact contact = contactService.getContact(contactId);
			boolean status = contactService.deleteContact(contactId);
			if(status) {
				response.setStatus("OK");
				response.setMessage("Contact with Phone number " + contact.getPhone_no() + " is deleted successfully.");
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else {
				response.setMessage("Contact delete request failed. Try again later");
				response.setStatus("FAILED");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
		}
		else {
			response.setMessage("User authorization failed");
			response.setStatus("FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping(path="/user/{userId}/contact/{contactId}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getContactById(@PathVariable String userId, @PathVariable long contactId, @RequestBody Map<String, String> payload){
		String[] requiredParam = {"accessToken"};
		PayloadVerifier payloadVerifier = new PayloadVerifier(payload, requiredParam);
		if(!payloadVerifier.isValid()) {
			ResponseParser response = new ResponseParser("Parameters insufficient for operation.", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		ResponseParser response = new ResponseParser();
		if(userService.authorizeUser(userId, payload.get("accessToken"))) {
			Contact contact = contactService.getContact(contactId);
			if(contact != null) {
				return new ResponseEntity<Object>(contact, HttpStatus.OK);
			}
			else {
				response.setMessage("Contact not found.");
				response.setStatus("FAILED");
				return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
			}
		}
		else {
			response.setMessage("User authorization failed");
			response.setStatus("FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
		}
	}
	
	//Contact Search 
	@PostMapping(path="/search", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> searchByPhoneNumber(@RequestBody Map<String, String> payload){
		String[] requiredParam = {"phoneNo"};
		PayloadVerifier payloadVerifier = new PayloadVerifier(payload, requiredParam);
		if(!payloadVerifier.isValid()) {
			ResponseParser response = new ResponseParser("Parameters insufficient for operation.", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		Contact contact = contactService.searchPhoneNo(payload.get("phoneNo"));
		if(contact != null) {
			return new ResponseEntity<Object>(contact, HttpStatus.OK);
		}
		else {
			ResponseParser response = new ResponseParser("Phone number not found.", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path="/user/{userId}/search", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> searchPhoneNoInUserContactList(@PathVariable String userId, @RequestBody Map<String, String> payload){
		String[] requiredParam = {"phoneNo", "accessToken"};
		PayloadVerifier payloadVerifier = new PayloadVerifier(payload, requiredParam);
		if(!payloadVerifier.isValid()) {
			ResponseParser response = new ResponseParser("Parameters insufficient for operation.", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		ResponseParser response = new ResponseParser();
		if(userService.authorizeUser(userId, payload.get("accessToken"))) {
			Contact contact = contactService.searchPhoneNoInUserContactList(userId, payload.get("phoneNo"));
			if(contact != null) {
				return new ResponseEntity<Object>(contact, HttpStatus.OK);
			}
			else {
				response.setMessage("Contact does not exist with this phone number.");
				response.setStatus("FAILED");
				return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
			}
		}
		else {
			response.setMessage("User authorization failed");
			response.setStatus("FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
		}
	}
}
