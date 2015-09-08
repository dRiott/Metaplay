package com.thoughtriott.metaplay.data.entities;

import java.util.Date;

public class User {

	//fields
	private int id;
	private String username;
	private String password;
	private String passwordConfirm;
	private String email;
	private Date registrationDate;
	
	
	public User(int id, String username, String password, String passwordConfirm, String email, Date registrationDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.email = email;
		this.registrationDate = registrationDate;
	}

	public User() {
	//default constructor for Spring to instantiate a User
	}

	//getters & setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", passwordConfirm="
				+ passwordConfirm + ", email=" + email + ", registrationDate=" + registrationDate + "]";
	}
}
