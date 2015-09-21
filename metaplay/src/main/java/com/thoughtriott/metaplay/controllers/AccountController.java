package com.thoughtriott.metaplay.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.entities.Role;
import com.thoughtriott.metaplay.data.services.AccountService;
import com.thoughtriott.metaplay.data.wrappers.CreateAccountWrapper;

@Controller
@RequestMapping("/account")
@SessionAttributes("createAccountWrapper")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addAccount(){
		System.out.println("Adding a new CreateAccountWrapper to the model with the @ModelAttribute annotation");
		return "account_add";
	}
	
	@RequestMapping("/review")
	public String review(HttpSession session, @Valid @ModelAttribute CreateAccountWrapper createAccountWrapper, Errors errors) {
		System.out.println("Invoking review() in AccountController");
		if(errors.hasErrors()) {
			return "account_add";
		}
		session.setAttribute("createAccountWrapper", createAccountWrapper);
		return "account_review";
	}

	@RequestMapping(value="/save")
	public String saveAccount(SessionStatus status, HttpSession session){
		System.out.println("invoking saveAcount");
		CreateAccountWrapper caw = (CreateAccountWrapper) session.getAttribute("createAccountWrapper");
		Account newAccount = accountService.createAccount(caw);
		status.setComplete();
		return "redirect:/account/" + newAccount.getAccountname();
	}
	
	@RequestMapping(value="/{accountName}")
	public String showProfile(@PathVariable String accountName, Model model) {
		Account account = accountService.findAccountByAccountname(accountName);
		List<Role> rolesList = account.getRoles();
		model.addAttribute("roles", rolesList);
		model.addAttribute(account);
		return "account_profile";
	}
	
	// LOGIN STUFF BEGINS HERE
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginPage(){
		return "account_login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String performLogin(@ModelAttribute Account account){
		System.out.println(account.getAccountname());
		System.out.println(account.getPassword());
		if(accountService.authenticate(account)) {
			return "redirect:/account/" + account.getAccountname();
		} else {
			return "redirect:login";
		}
	}	
	
	//Logout
	@RequestMapping("/byebye")
	public String findGroupMembers(){
		return "404";
	}
	
	@ModelAttribute("createAccountWrapper")
	public CreateAccountWrapper getCreateAccountWrapper() {
		return new CreateAccountWrapper();
	}
	
	@ModelAttribute("account")
	public Account getAccount() {
		return new Account();
	}
	
}
