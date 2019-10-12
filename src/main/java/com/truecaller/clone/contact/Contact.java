package com.truecaller.clone.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.truecaller.clone.user.User;

@Table(name="Contact")
@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long id;
	private String name;
	@Column(name="phoneno")
	private String phoneNo;
	
	@ManyToOne
	@JoinColumn(name="userid")
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	public Contact() {
		
	}
	
	public Contact(String name, String phone_no, String userId) {
		super();
		this.name = name;
		this.phoneNo = phone_no;
		this.user = new User(userId);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_no() {
		return phoneNo;
	}

	public void setPhone_no(String phone_no) {
		this.phoneNo = phone_no;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
