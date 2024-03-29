package com.truecaller.clone.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String >{
	
	public User findByPhoneNoAndPassword(String phoneNo, String password);
	
}
