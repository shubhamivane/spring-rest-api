package com.truecaller.clone.user;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="User")
@Entity
public class User {
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phoneno", nullable=false, length=200)
	private String phoneNo;
	
	@Column(name="password")
	@JsonIgnore
	private String password;
	
	//Refer to this : https://stackoverflow.com/questions/50567041/spring-boot-jpa-unknown-column-in-field-list/50568150
	@Column(name="accesstoken", nullable=false, length=200)
	private String accessToken;

	public User() {
		
	}
	
	public User(String id) {
		this.setId(id);
	}
	
	public User(String name, String phoneNo, String password) {
		this.setId(UUID.randomUUID().toString().replace("-", ""));
		this.setName(name);
		this.setPhoneNo(phoneNo);
		this.setPassword(password);
		this.setAccessToken(UUID.randomUUID().toString());
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public void changeAccessToken() {
		this.accessToken = UUID.randomUUID().toString();
	}

}
