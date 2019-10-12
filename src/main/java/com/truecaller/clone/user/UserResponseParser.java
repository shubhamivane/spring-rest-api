package com.truecaller.clone.user;

public class UserResponseParser {
	private String name;
	private String phoneNo;
	private String id;
	
	public UserResponseParser(){
		
	}
	
	public UserResponseParser(String id, String name, String phoneNo){
		this.setId(id);
		this.name = name;
		this.phoneNo = phoneNo;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
