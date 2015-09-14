package com.thoughtriott.metaplay.data.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Account;

public class AccountService {

	@PersistenceContext
	private EntityManager em;
	
	public AccountService () {
		//no-arg constructor
	}
	
	@Transactional
	public Account createAccount(String accountname, String password) {
		em.clear();
		Account a = new Account();
		a.setAccountname(accountname);
		a.setPassword(password);
		em.persist(a);
		return a;
	}
	
	//finds all Accounts in Account table
	public Collection<Account> findAllAsCollection() {
		return em.createQuery("SELECT a FROM Account a ORDER BY a.accountname", Account.class).getResultList();
	}
	
	//grabs all the Accounts belonging to a certain State
	public Account findAccountByAccountname(String accountname) {
		return (Account) em.createQuery("SELECT a FROM Account a WHERE a.accountname = :accountname").setParameter("accountname", accountname).getSingleResult();
	}
	
	//return a string of all of the cities in that state
	@SuppressWarnings("unchecked")
	public String toStringAccounts() {
		List<Account> accounts = em.createQuery("SELECT a FROM Account a").getResultList();
		int size = accounts.size();
		int indexCounter = 0;
		String accountsString = "";
		
		Iterator<Account> it = accounts.iterator();
		while(it.hasNext()) {
			Account currentAccount = it.next();
			indexCounter++;
			if (size==1) {
				accountsString = "{" + currentAccount.getAccountname() + "}";
			} else if(indexCounter < size && indexCounter != 1) {
				accountsString = accountsString + ", " + currentAccount.getAccountname();
			} else if (indexCounter == size){
				accountsString = accountsString + ", " + currentAccount.getAccountname() + "}";
			} else {
				accountsString = "{" + currentAccount.getAccountname();
			}
		}
		return accountsString;
	}
	
	//find Account by Id
	public Account findAccountById(int id) {
		return (Account) em.createQuery("SELECT a FROM Account a WHERE a.id = :id").setParameter("id", id).getSingleResult();
	}

}