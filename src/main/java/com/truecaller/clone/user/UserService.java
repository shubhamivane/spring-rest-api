package com.truecaller.clone.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truecaller.clone.user.User;

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
	
	public boolean createUser(User user) {
		try {
			userRepository.save(user);
			return true;
		}
		catch(Exception e) {
			return false;
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
