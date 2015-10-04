package com.thoughtriott.metaplay.data.repositories;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.wrappers.CreateAccountWrapper;

@Repository
public class AccountService {

	@PersistenceContext
	private EntityManager em;

	public AccountService() {
		// no-arg constructor
	}

	public boolean authenticate(Account account) {
		if (findAccountByAccountname(account.getAccountname()) != null) {
			return true;
		} else {
			return false;
		}
	}

	// --------------------- Creates ----------------------
	@Transactional
	public Account createAccount(String accountname, String password) {
		em.clear();
		Account a = new Account();
		a.setAccountname(accountname);
		a.setPassword(password);
		em.persist(a);
		em.close();
		return a;
	}

	@Transactional
	public Account createAccount(Account account) {
		em.clear();
		em.persist(account);
		em.close();
		return account;
	}

	@Transactional
	public Account createAccount(CreateAccountWrapper caw) {

		Account account = new Account();
		String accountname = caw.getAccountname();
		System.out.println("setting Accountname...");
		account.setAccountname(accountname);

		// some kind of password confirmation business here...
		try {
			String password = caw.getPassword();
			String confirmPassword = caw.getConfirmPassword();
			if (!confirmPassword.equals(password)) {
				throw new RuntimeException();
			}
			System.out.println("setting Password...");
			account.setPassword(password);
		} catch (RuntimeException re) {
			System.out.println(re.getMessage());
		}

		String email = caw.getEmail();
		System.out.println("setting Email...");
		account.setEmail(email);

		System.out.println("setting Registration Date...");
		account.setRegistrationDate(new Date());

		return createAccount(account);
	}

// --------------------- Queries -----------------

	// finds all Accounts in Account table
	public List<Account> findAllAsList() {
		List<Account> acctList = (List<Account>) em
				.createQuery("SELECT a FROM Account a ORDER BY a.accountname", Account.class).getResultList();
		if (acctList.size() == 0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return acctList;
		}
	}

	// grabs all the Accounts belonging to a certain State
	public Account findAccountByAccountname(String accountname) {
		@SuppressWarnings("unchecked")
		List<Account> acctList = (List<Account>) em
				.createQuery("SELECT a FROM Account a WHERE a.accountname = :accountname")
				.setParameter("accountname", accountname).getResultList();
		if (acctList.size() == 0) {
			return null;
		} else if (acctList.size() > 1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return acctList.get(0);
		} else {
			return acctList.get(0);
		}
	}

	// find Account by Id
	public Account findAccountById(int id) {
		@SuppressWarnings("unchecked")
		List<Account> acctList = (List<Account>) em.createQuery("SELECT a FROM Account a WHERE a.id = :id")
				.setParameter("id", id).getResultList();
		if (acctList.size() == 0) {
			System.out.println("The results list was empty.");
			return null;
		} else if (acctList.size() > 1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return acctList.get(0);
		} else {
			return acctList.get(0);
		}
	}

// ----------------------- to String ----------------

	// return a string of all of the cities in that state
	@SuppressWarnings("unchecked")
	public String toStringAccounts() {
		List<Account> accounts = em.createQuery("SELECT a FROM Account a").getResultList();
		int size = accounts.size();
		int indexCounter = 0;
		String accountsString = "";

		Iterator<Account> it = accounts.iterator();
		while (it.hasNext()) {
			Account currentAccount = it.next();
			indexCounter++;
			if (size == 1) {
				accountsString = "{" + currentAccount.getAccountname() + "}";
			} else if (indexCounter < size && indexCounter != 1) {
				accountsString = accountsString + ", " + currentAccount.getAccountname();
			} else if (indexCounter == size) {
				accountsString = accountsString + ", " + currentAccount.getAccountname() + "}";
			} else {
				accountsString = "{" + currentAccount.getAccountname();
			}
		}
		return accountsString;
	}

}