package com.thoughtriott.metaplay.data.wrappers;

import javax.validation.constraints.NotNull;

public class RoleWrapper {

// --------------------------Fields--------------------------	
	@NotNull
	private String accountname;
	
	@NotNull
	private String roleName;
	
	@NotNull
	private String actionOption;
	
// --------------------------Constructors--------------------------
	public RoleWrapper() {
	}
	
	public RoleWrapper(String accountname, String roleName) {
		super();
		this.accountname = accountname;
		this.roleName = roleName;
	}

//--------------------------Getters & Setters--------------------------	
	public String getAccountname() {
		return accountname;
	}
	
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getActionOption() {
		return actionOption;
	}

	public void setActionOption(String actionOption) {
		this.actionOption = actionOption;
	}

}
