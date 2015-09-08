package com.thoughtriott.metaplay.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.User;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

	//User Add Page
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String findRecordLabels(Model model){
		System.out.println("Adding a new User to the model with the @ModelAttribute annotation");
		System.out.println("Invoking the addPlaylist() method.");
		return "user_add";
	}
	
	//User reviews their credentials before Submit
	@RequestMapping("/review")
	public String review(@ModelAttribute User user) {
		System.out.println("Invoking review() in UserController");
		return "user_review";
	}

	//Saves the User to the DB?
	@RequestMapping(value="/save")
	public String saveUser(@ModelAttribute User user, SessionStatus status){
		System.out.println("invoking saveUser");
		System.out.println("setting Registration Date...");		
		user.setRegistrationDate(new Date());
		System.out.println(user); //invoking toString Method
		//setComplete wipes the session of the info that we passed to the review page
		//so that when we redirect to the /artist/add page, a blank form is displayed.
		status.setComplete();
		return "redirect:/user/login";
	}
	
	//User Login Page
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin() {
		return "login";
	}
	
	//Login POST — Check credentials with DB?
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin() {
		return "404";
	}
	
	//Logout
	@RequestMapping("/byebye")
	public String findGroupMembers(){
		return "404";
	}
	
	//adds a new User() to the model
	@ModelAttribute("user")
	public User getUser() {
		System.out.println("Creating new User();");
		return new User();
	}
	
}
