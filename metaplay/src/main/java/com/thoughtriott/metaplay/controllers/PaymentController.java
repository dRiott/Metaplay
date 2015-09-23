package com.thoughtriott.metaplay.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.CreditCard;

@Controller
@RequestMapping("/payment")
@SessionAttributes("creditCard")
public class PaymentController {
	
	
	@RequestMapping(value="/process", method=RequestMethod.GET)
	public String getPaymentPage(){
		return "payment_add";
	}
	
	@RequestMapping(value="/process", method=RequestMethod.POST)
	public String processCard(@ModelAttribute CreditCard card, SessionStatus status) {
		System.out.println("invoking processCard");
		System.out.println(card);
//		CreditCard newCard = creditCardService.createCreditCard(card);
		status.setComplete();
		return "redirect:/payment/process";
	}
//
//	@RequestMapping(value="/save")
//	public String saveAccount(SessionStatus status, HttpSession session){
//		System.out.println("invoking saveAcount");
//		CreateAccountWrapper caw = (CreateAccountWrapper) session.getAttribute("createAccountWrapper");
//		Account newAccount = accountService.createAccount(caw);
//		status.setComplete();
//		return "redirect:/account/" + newAccount.getAccountname();
//	}
//	
//	@RequestMapping(value="/{accountName}")
//	public String showProfile(@PathVariable String accountName, Model model) {
//		Account account = accountService.findAccountByAccountname(accountName);
//		List<Role> rolesList = account.getRoles();
//		model.addAttribute("roles", rolesList);
//		model.addAttribute(account);
//		return "account_profile";
//	}
//	
//	// LOGIN STUFF BEGINS HERE
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String getLoginPage(){
//		return "account_login";
//	}
//	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String performLogin(@ModelAttribute Account account){
//		System.out.println(account.getAccountname());
//		System.out.println(account.getPassword());
//		if(accountService.authenticate(account)) {
//			return "redirect:/account/" + account.getAccountname();
//		} else {
//			return "redirect:login";
//		}
//	}	
//	
//	//Logout
//	@RequestMapping("/byebye")
//	public String findGroupMembers(){
//		return "404";
//	}
	
	@ModelAttribute("creditCard")
	public CreditCard getCreditCard() {
		return new CreditCard();
	}
	
	@ModelAttribute("cardTypeOptions")
	public List<String> getTypes () {
		return new LinkedList<>(Arrays.asList(new String[] { 
				"Visa", "Discover", "American Express", "Other"
		}));
	}	
	
}
