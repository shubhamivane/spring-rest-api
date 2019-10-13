package com.truecaller.clone.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElse(null);
	}
	
	public User authenticateUser(String phoneNo, String password) {
		User user = userRepository.findByPhoneNoAndPassword(phoneNo, password);
		return user;
	}
	
	public String createUser(User user) {
		try {
			userRepository.save(user);
			return "User account created.";
		}
		catch(DataIntegrityViolationException dive) {
			System.out.println(dive);
			return "Phone number is already registered.";
		}
		catch(Exception e) {
			System.out.print(e);
			return "Something went wrong.";
		}
	}
	
	public boolean updateUser(User user) {
		try {
			userRepository.save(user);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean authorizeUser(String id, String accessToken) {
		User user = userRepository.findById(id).orElse(null);
		if(user.equals(null)) {
			return false;
		}
		else {
			return user.getAccessToken().equals(accessToken);
		}
	}

	public boolean deleteUser(String id) {
		try {
			userRepository.deleteById(id);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
