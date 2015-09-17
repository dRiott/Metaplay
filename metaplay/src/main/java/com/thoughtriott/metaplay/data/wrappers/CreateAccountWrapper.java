package com.thoughtriott.metaplay.data.wrappers;

public class CreateAccountWrapper {

// --------------------------Fields--------------------------	
	
	private String accountname;
	private String password;
	private String confirmPassword;
	private String email;

	
// --------------------------Constructors--------------------------

	public CreateAccountWrapper(String accountname, String password, String confirmPassword, String email) {
		super();
		this.accountname = accountname;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}
	
	public CreateAccountWrapper() {
	}
	
//--------------------------Getters & Setters--------------------------	
	
	public String getAccountname() {
		return accountname;
	}
	
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}