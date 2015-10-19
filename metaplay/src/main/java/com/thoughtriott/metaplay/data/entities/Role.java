package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "role")
public class Role extends MetaplayEntity {

// --------------------------Constructors--------------------------
	public Role() {
				
	}	
	
// --------------------------Fields--------------------------
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	@ManyToMany(mappedBy = "roles")
	@JsonBackReference
	private Collection<Account> accounts;

//	private String name;
	private String description;
	
	@Column(name="entity_type")
	private String entityType = "role";

//--------------------------Getters & Setters--------------------------
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

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
		if(getAccounts()!=null) {
		Iterator<Account> it = getAccounts().iterator();
		String accountsString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			Account currentAccount = it.next();
			if(accountsString.length() > 1) {
			accountsString = accountsString + ", " + currentAccount.getAccountname();
			} else if (!it.hasNext()) {
				accountsString = accountsString + ", " + currentAccount.getAccountname() + "}";
			} else {
				accountsString = "Accounts: {" + currentAccount.getAccountname();
			}
		}
		return accountsString;
		} return "No accounts.";
	}
//--------------------------toString()--------------------------

	//In Account, roles.size();
	@Override
	public String toString() {
		return "Role [id=" + id + ", accounts=" + this.getAccountsToString() + ", name=" + name + ", description=" + description + "]";
	}

}
