package com.thoughtriott.metaplay.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
import com.thoughtriott.metaplay.data.repositories.AccountRepository;
import com.thoughtriott.metaplay.data.repositories.RoleRepository;

@Controller
@RequestMapping("/account")
@SessionAttributes(value = { "createAccountWrapper, loginStatus, counter" })
public class AccountController {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	RoleRepository roleRepository;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAccount() {
		System.out.println(
				"AccountController: addAccount() - Adding a new Account to the model with the @ModelAttribute annotation");
		return "account_add";
	}

	@RequestMapping("/review")
	public String review(HttpSession session, @Valid @ModelAttribute Account account, Errors errors) {
		System.out.println("AccountController: review() - invoked");
		if (errors.hasErrors()) {
			return "account_add";
		}
		session.setAttribute("account", account);
		return "account_review";
	}

	@RequestMapping(value = "/save")
	public String saveAccount(SessionStatus status, HttpSession session, Model model) {
		System.out.println("AccountController: saveAccount() - invoking saveAcount");
		Account newAccount = (Account) session.getAttribute("account");
		newAccount.setRegistrationDate(new Date());
		newAccount.setEnabled(true);
		Account savedAccount = accountRepository.saveAndFlush(newAccount);
		Role role = roleRepository.findRoleOrderByName("Lurker").get(0);
		savedAccount.addRole(role);
		//for some reason, needed to save the account first, then add the role and save again.
		accountRepository.saveAndFlush(savedAccount);
//		model.addAttribute("accountId", savedAccount.getId());
//		return "redirect:/account/{accountId}";
		status.setComplete();
		return "redirect:/account/login";
	}

	// LOGIN STUFF BEGINS HERE
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(HttpSession session) {
		if (session.getAttribute("loginStatus") == null) {
			int counter = 0;
			session.setAttribute("counter", counter);
		}
		System.out.println("AccountController: getLoginPage() - SessionAttribute \"loginStatus\": "
				+ session.getAttribute("loginStatus"));
		return "account_login";
	}
	
	@RequestMapping("/profile")
	public String getUserAndRedirect(@AuthenticationPrincipal User activeUser, Model model) {
		Account activeAccount = accountRepository.findAccountByAccountname(activeUser.getUsername()).get(0);
		model.addAttribute("accountId", activeAccount.getId());
		return "redirect:/account/{accountId}";
	}
	
	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public String showProfile(@PathVariable Integer accountId, Model model, @AuthenticationPrincipal User activeUser) {
		int activeId = accountRepository.findAccountByAccountname(activeUser.getUsername()).get(0).getId();
		if(accountId != activeId) {
			//need to throw an error here because a user tried to access a profile page that was not there own.
			model.addAttribute("accountId", activeId);
			return "redirect:/account/{accountId}";
		} else {
		Account account = accountRepository.getOne(accountId);
		List<Role> rolesList = account.getRoles();
		model.addAttribute("roles", rolesList);
		model.addAttribute(account);
		return "account_profile";
		}
	}
	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String performLogin(@ModelAttribute Account accountToLogin, HttpSession session, WebRequest request, SessionStatus status, Model model){
//		String loginAccountname =  accountToLogin.getAccountname();
//		String loginPassword = accountToLogin.getPassword();
//		System.out.println("AccountController: performLogin() - Accountname: " + loginAccountname);
//		System.out.println("AccountController: performLogin() - Password: " + loginPassword);
//		if(accountRepository.findAccountByAccountname(loginAccountname)!=null) {
//			Account dbAccount = accountRepository.findAccountByAccountname(loginAccountname).get(0);
//			String dbAccountPassword = dbAccount.getPassword();
//			if(loginPassword.equals(dbAccountPassword)) {
//				status.setComplete();
//				request.removeAttribute("loginStatus", WebRequest.SCOPE_SESSION);
//				System.out.println("AccountController: performLogin() - SessionAttribute \"loginStatus\": " + session.getAttribute("loginStatus"));
//				model.addAttribute("accountId", dbAccount.getId());
//				return "redirect:/account/{accountId}";
//			} else {
//				System.out.println("AccountController: performLogin() - login failed...");
//				int counter = (int) session.getAttribute("counter");
//				if(counter >= 3) 
//					return "404";
//				counter++;
//				System.out.println(counter);
//				String loginStatus = "fuckedUp";
//				session.setAttribute("loginStatus", loginStatus);
//				session.setAttribute("counter", counter);
//				System.out.println("Counter in session: " + session.getAttribute("counter"));
//				return "redirect:login";
//			} 	
//		} else return "redirect:login";
//	}

	// Logout
	@RequestMapping("/byebye")
	public String findGroupMembers() {
		return "404";
	}
	
	@ModelAttribute("account")
	public Account getAccount() {
		return new Account();
	}

}
