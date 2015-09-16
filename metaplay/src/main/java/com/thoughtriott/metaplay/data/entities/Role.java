package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

// --------------------------Constructors--------------------------
	public Role() {
				
	}	
	
// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany(mappedBy = "roles")
	private Collection<Account> accounts;

	private String name;
	private String description;

//--------------------------Getters & Setters--------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//--------------------------Collection Adders and Removers--------------------------
	//adds an Account to Collection<Account>
	public void addAccount(Account account) {
		if (getAccounts()!=null && !getAccounts().contains(account)) {
			getAccounts().add(account);
			if (!account.getRoles().contains(this)) {
				account.addRole(this);
			}
		}
	}
			
	// removes an Account from Collection<Account>.
	public void removeAccount(Account account) {
		if (getAccounts()!=null && getAccounts().contains(account)) {
			getAccounts().remove(account);
			if (account.getRoles().contains(this)) {
				account.removeRole(this);
			}
		}	
	}

//--------------------------Collection Printers--------------------------

	public String getAccountsToString () {
		if(getAccounts()!=null) {
		Iterator<Account> it = getAccounts().iterator();
		String accountsString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			if(accountsString.length() > 1) {
			accountsString = accountsString + ", " + it.next().getAccountname();
			} else if (!it.hasNext()) {
				accountsString = accountsString + ", " + it.next().getAccountname() + "}";
			} else {
				accountsString = "Accounts: {" + it.next().getAccountname();
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
