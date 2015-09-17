package com.thoughtriott.metaplay.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.services.AccountService;
import com.thoughtriott.metaplay.data.wrappers.CreateAccountWrapper;

@Controller
@RequestMapping("/account")
@SessionAttributes("createAccountWrapper")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	//User Add Page
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addAccount(){
		System.out.println("Adding a new User to the model with the @ModelAttribute annotation");
		return "account_add";
	}
	
	//User reviews their credentials before Submit
	@RequestMapping("/review")
	public String review(HttpSession session, @ModelAttribute CreateAccountWrapper createAccountWrapper) {
		System.out.println("Invoking review() in AccountController");
		session.setAttribute("createAccountWrapper", createAccountWrapper);
		return "account_review";
	}

	//Saves the Account to the DB?
	@RequestMapping(value="/save")
	public String saveAccount(@ModelAttribute CreateAccountWrapper createAccountWrapper, Errors errors, SessionStatus status, HttpSession session){
		System.out.println("invoking saveAcount");
		
		//validation. The errors parameter must be directly after the ModelAttribute with the @Valid annotation.
		if(!errors.hasErrors()) {
			System.out.println("The account was validated.");
		} else{
			System.out.println("The account did not validate.");
		}
		
		Account account = new Account();
		CreateAccountWrapper caw = (CreateAccountWrapper) session.getAttribute("createAccountWrapper");
		
		String accountname = caw.getAccountname();
		System.out.println("setting Accountname...");
		account.setAccountname(accountname);
		
		//some kind of password confirmation business here...
		String password = caw.getPassword();
		String confirmPassword = caw.getConfirmPassword();	
		if(!confirmPassword.equals(password)) {
			throw new RuntimeException();
		}
		System.out.println("setting Password...");
		account.setPassword(password);
		
		String email = caw.getEmail();
		System.out.println("setting Email...");
		account.setEmail(email);
	
		System.out.println("setting Registration Date...");		
		account.setRegistrationDate(new Date());
		System.out.println(account);
		
		accountService.createAccount(account);
		System.out.println(accountService.findAccountByAccountname(accountname));
		status.setComplete();
		return "redirect:/account/login";
	}
	
	//User Login Page
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin() {
		
		//check credentials with DB
		return "404";
	}
	
	//Logout
	@RequestMapping("/byebye")
	public String findGroupMembers(){
		return "404";
	}
	
	//adds a new CreateAccountWrapper() to the model
	@ModelAttribute("createAccountWrapper")
	public CreateAccountWrapper getCreateAccountWrapper() {
		return new CreateAccountWrapper();
	}
	
}
