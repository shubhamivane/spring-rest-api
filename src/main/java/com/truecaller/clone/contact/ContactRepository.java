package com.truecaller.clone.contact;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long>{
	public List<Contact> findByUserId(String id);
	public Contact findByphoneNo(String phoneNo);
	public Contact findByUserIdAndPhoneNo(String id, String phoneNo);
	public Optional<Contact> findById(long id);
	public void deleteById(long id);
}
