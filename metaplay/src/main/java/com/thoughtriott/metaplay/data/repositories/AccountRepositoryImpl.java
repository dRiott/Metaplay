package com.thoughtriott.metaplay.data.repositories;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.Account;

public class AccountRepositoryImpl implements AccountRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public String findAllToFormattedString() {
		List<Account> accounts = accountRepository.findAll();
		int indexCounter = 0;
		String accountsString = "";

		Iterator<Account> it = accounts.iterator();
		while (it.hasNext()) {
			Account currentAccount = it.next();
			indexCounter++;
			if (accounts.size() == 1) {
				accountsString = "{" + currentAccount.getAccountname() + "}";
			} else if (indexCounter < accounts.size() && indexCounter != 1) {
				accountsString = accountsString + ", " + currentAccount.getAccountname();
			} else if (indexCounter == accounts.size()) {
				accountsString = accountsString + ", " + currentAccount.getAccountname() + "}";
			} else {
				accountsString = "{" + currentAccount.getAccountname();
			}
		}
		return accountsString;
	}
}
