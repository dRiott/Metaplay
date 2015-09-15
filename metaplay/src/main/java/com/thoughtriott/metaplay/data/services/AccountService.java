package com.thoughtriott.metaplay.data.services;

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
	
//------------------------------- Creates ---------------------------------------	
	
	@Transactional
	public Account createAccount(String accountname, String password) {
		em.clear();
		Account a = new Account();
		a.setAccountname(accountname);
		a.setPassword(password);
		em.persist(a);
		return a;
	}
	
//------------------------------- Queries ---------------------------------------	
	
	//finds all Accounts in Account table
	public List<Account> findAllAsList() {
		List<Account> acctList = (List<Account>) em.createQuery("SELECT a FROM Account a ORDER BY a.accountname", Account.class).getResultList();
		if(acctList.size()==0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return acctList;
		}
	}
	
	//grabs all the Accounts belonging to a certain State
	public Account findAccountByAccountname(String accountname) {
		@SuppressWarnings("unchecked")
		List<Account> acctList = (List<Account>) em.createQuery("SELECT a FROM Account a WHERE a.accountname = :accountname").setParameter("accountname", accountname).getResultList();
		if(acctList.size()==0) {
			return null;
		} else if(acctList.size()>1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return acctList.get(0);
		} else {
			return acctList.get(0);
		}
	}
	
	//find Account by Id
	public Account findAccountById(int id) {
		@SuppressWarnings("unchecked")
		List<Account> acctList = (List<Account>) em.createQuery("SELECT a FROM Account a WHERE a.id = :id").setParameter("id", id).getResultList();
		if(acctList.size()==0) {
			System.out.println("The results list was empty.");
			return null;
		} else if(acctList.size()>1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return acctList.get(0);
		} else {
			return acctList.get(0);
		}
	}
	
//------------------------------- to String ---------------------------------------		
	
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
	

}