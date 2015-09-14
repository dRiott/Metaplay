package com.thoughtriott.metaplay.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Account;

@Controller
@RequestMapping("/account")
@SessionAttributes("account")
public class AccountController {

	//User Add Page
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String findRecordLabels(Model model){
		System.out.println("Adding a new User to the model with the @ModelAttribute annotation");
		System.out.println("Invoking the addPlaylist() method.");
		return "account_add";
	}
	
	//User reviews their credentials before Submit
	@RequestMapping("/review")
	public String review(@ModelAttribute Account account) {
		System.out.println("Invoking review() in AccountController");
		return "account_review";
	}

	//Saves the Account to the DB?
	@RequestMapping(value="/save")
	public String saveAccount(@ModelAttribute Account account, SessionStatus status){
		System.out.println("invoking saveAcount");
		System.out.println("setting Registration Date...");		
		account.setRegistrationDate(new Date());
		System.out.println(account); //invoking toString Method
		//setComplete wipes the session of the info that we passed to the review page
		//so that when we redirect to the /artist/add page, a blank form is displayed.
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
	
	//adds a new Account() to the model
	@ModelAttribute("account")
	public Account getAccount() {
		System.out.println("Creating new Account();");
		return new Account();
	}
	
}
