package com.thoughtriott.metaplay.data.repositories.jpa;

import com.thoughtriott.metaplay.data.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepositoryCustom {
	
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
	
	//returns List<String> of each Role
	@Override	
	public List<String> findAllToListString() {
		List<Account> accountList = accountRepository.findAll();
		Iterator<Account> it = accountList.iterator();
		List<String> accountStringList = new ArrayList<>();
		while (it.hasNext()) {
			Account account = it.next();
			accountStringList.add(account.getAccountname());
		}
		if (accountList.size() == 0) {
			System.out.println("The results list was empty.");
			accountStringList.add("No Accounts exist, add one!");
			accountStringList.add("** New Account **");
			return accountStringList;
		} else {
			accountStringList.add("** New Account **");
			return accountStringList;
		}
	}
	
	
}
