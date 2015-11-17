package com.thoughtriott.metaplay.controllers;

import com.thoughtriott.metaplay.data.entities.CreditCard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
		System.out.println("PaymentController - processCard():  invoking");
		status.setComplete();
		return "redirect:/payment/process";
	}

	// ------------------------------ Model Attributes ------------------------------
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
