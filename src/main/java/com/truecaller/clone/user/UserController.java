package com.truecaller.clone.user;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truecaller.clone.parser.PayloadVerifier;
import com.truecaller.clone.parser.ResponseParser;

@RequestMapping
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path="/user/{id}")
	public ResponseEntity<Object> getUser(@PathVariable String id){
		User user = userService.getUser(id);
		if(user != null) {
			UserResponseParser userResponse = new UserResponseParser(id, user.getName(), user.getPhoneNo());
			return new ResponseEntity<Object>(userResponse, HttpStatus.OK);
		}
		else {
			ResponseParser error = new ResponseParser("User not found.", "FAILED");
			return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path="/accesstoken", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAccessToken(@RequestBody Map<String, String> payload){
		String[] requiredParam = {"phoneNo", "password"};
		PayloadVerifier payloadVerifier = new PayloadVerifier(payload, requiredParam);
		if(!payloadVerifier.isValid()) {
			ResponseParser response = new ResponseParser("Parameters insufficient for operation.", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		User user = userService.authenticateUser(payload.get("phoneNo"), payload.get("password"));
		if(user != null) {
			user.changeAccessToken();
			if(userService.updateUser(user)) {
				return new ResponseEntity<Object>(user, HttpStatus.OK);
			}
			else {
				ResponseParser response = new ResponseParser("Something went wrong. Try againg later.", "FAILED");
				return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		ResponseParser response = new ResponseParser("Phone number or password is incorrect.", "FAILED");
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(path="/user", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@RequestBody Map<String, String> payload){
		String[] requiredParam = {"name", "phoneNo", "password"};
		PayloadVerifier payloadVerifier = new PayloadVerifier(payload, requiredParam);
		ResponseParser response = new ResponseParser();
		if(!payloadVerifier.isValid()) {
			response.setMessage("Parameters insufficient for operation.");
			response.setStatus("FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		User user = new User(payload.get("name"), payload.get("phoneNo"), payload.get("password"));
		String message = userService.createUser(user);
		if(message.equals("User account created.")) {
			response.setMessage(message);
			response.setStatus("OK");
			return new ResponseEntity<Object>(response, HttpStatus.CREATED);
		}
		else if(message.equals("Phone number is already registered.")) {
			response.setMessage(message);
			response.setStatus("FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		else {
			response.setMessage("Something went wrong.");
			response.setStatus("FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/user/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody Map<String, String> payload){
		ResponseParser response = new ResponseParser();
		if(userService.authorizeUser(id, payload.get("accessToken"))) {
			if(payload.containsKey("name") && payload.containsKey("new_name")) {
				User user = userService.getUser(id);
				user.setName(payload.get("new_name"));
				boolean status = userService.updateUser(user);
				if(status) {
					response.setMessage("Name is updated successfully.");
					response.setStatus("OK");
				}
				else {
					response.setMessage("Name update request failed. Try again..");
					response.setStatus("FAILED");
				}
			}
			else if(payload.containsKey("password") && payload.containsKey("new_password")) {
				User user = userService.getUser(id);
				user.setPassword(payload.get("new_password"));
				boolean status = userService.updateUser(user);
				if(status) {
					response.setMessage("Password updated successfully.");
					response.setStatus("OK");
				}
				else {
					response.setMessage("Password update request failed.Try again.");
					response.setStatus("FAILED");
				}
			}
			else {
				response.setMessage("Invalid request or params.");
				response.setStatus("FAILED");
			}
		}
		else {
			response = new ResponseParser("User authorization failed", "FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
		}
		if(response.getStatus().equals("OK")) {
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/user/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteUser(@PathVariable String id, @RequestBody Map<String, String> payload){
		ResponseParser response = new ResponseParser();
		String[] requiredParam = {"accessToken"};
		PayloadVerifier payloadVerifier = new PayloadVerifier(payload, requiredParam);
		if(!payloadVerifier.isValid()) {
			response.setMessage("Parameters insufficient for operation.");
			response.setStatus("FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		if(userService.authorizeUser(id, payload.get("accessToken"))){
			boolean status = userService.deleteUser(id);
			if(status) {
				response.setMessage("User with " + id + " deleted successfully.");
				response.setStatus("OK");
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else {
				response.setMessage("User account delete request failed.");
				response.setStatus("FAILED");
				return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
			}
		}
		else {
			response.setMessage("User authorization failed");
			response.setStatus("FAILED");
			return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
		}
	}
}
