package com.thoughtriott.metaplay.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "role")
@JsonIgnoreProperties({"entityType", "password", "accounts"})
public class Role extends MetaplayEntity {

// --------------------------Constructors--------------------------
	public Role() {
				
	}	
	
// --------------------------Fields--------------------------
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<Account> accounts;

//	private String name;
	private String description;
	
	@Column(name="entity_type")
	private String entityType = "role";

//--------------------------Getters & Setters--------------------------
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	//--------------------------Collection Adders and Removers--------------------------
	//adds an Account to Collection<Account>
	public Role addAccount(Account account) {
		if (getAccounts()!=null && !getAccounts().contains(account)) {
			getAccounts().add(account);
			if (!account.getRoles().contains(this)) {
				account.addRole(this);
			}
		}
		return this;
	}
			
	// removes an Account from Collection<Account>.
	public Role removeAccount(Account account) {
		if (getAccounts()!=null && getAccounts().contains(account)) {
			getAccounts().remove(account);
			if (account.getRoles().contains(this)) {
				account.removeRole(this);
			}
		}	
		return this;
	}

//--------------------------Collection Printers--------------------------
	
	public String getAccountsToString () {
		if(accounts!=null) {
			Iterator<Account> it = accounts.iterator();
			String accountsString = "";
			int size = accounts.size();
			while(it.hasNext()) {
				Account currentAccount = it.next();
				if (size == 0) {
					accountsString = "No accounts.";
				} else if(getAccounts().size()==1) {
					accountsString = "{" + currentAccount.getName() + "}";
				} else {
					if(accounts.indexOf(currentAccount) == 0) {
						accountsString = "{" + currentAccount.getName();
					} else if (!it.hasNext()) {
						accountsString = accountsString + ", " + currentAccount.getName() + "}";
					} else {
						accountsString = accountsString + ", " + currentAccount.getName();
					}
				}
			} return accountsString;
		} return "Accounts list was null.";
	}
	
//--------------------------toString()--------------------------
	@Override
	public String toString() {
		return "Role [id=" + id + ", accounts=" + getAccountsToString() + ", name=" + name + ", description=" + description + "]";
	}

}
