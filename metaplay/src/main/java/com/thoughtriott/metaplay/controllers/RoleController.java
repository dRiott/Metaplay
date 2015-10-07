package com.thoughtriott.metaplay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.entities.Role;
import com.thoughtriott.metaplay.data.repositories.AccountRepository;
import com.thoughtriott.metaplay.data.repositories.RoleRepository;
import com.thoughtriott.metaplay.data.wrappers.RoleWrapper;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AccountRepository accountRepository;

	@RequestMapping("/add")
	public String addRole(){
		return "role_add";
	}
	
	@RequestMapping(value="/save")
	public String saveRole(@ModelAttribute Role role, SessionStatus status){
		System.out.println("RoleController: saveRole() - invoking...");
		roleRepository.saveAndFlush(role);
		status.setComplete();
		return "redirect:/role/add";
	}
	
	@RequestMapping(value="/assign", method=RequestMethod.GET)
	public String getAssignRole() {
		return "role_assign";
	}
	
	@RequestMapping(value="/assign", method=RequestMethod.POST) 
	public String saveAssignRole(@ModelAttribute RoleWrapper roleWrapper) {
		System.out.println("RoleController: saveAssignRole() - invoking...");
		
		Account account = accountRepository.findAccountByAccountname(roleWrapper.getAccountname()).get(0);
		Role role = roleRepository.findRoleOrderByName(roleWrapper.getRoleName()).get(0);
		
		account.addRole(role);
		accountRepository.saveAndFlush(account);

		return "redirect:/role/assign";
	}
	
	
	
//	@RequestMapping("/find")
//	public String findLocation(Model model){
//		
//		//"portland" attribute
//		Location portland = locationRepository.findLocationByCityAndState("Portland", "Oregon");
//		model.addAttribute("currentLocation", portland);
//
//		//"all" attribute
//		String all = locationRepository.findAllToFormattedString();
//		model.addAttribute("all", all);
//		
//		//test attribute
	
//		//"states" attribute
//		Iterator<String> it = locationRepository.findAllStatesToListString().iterator();
//		String states = "";
//		while(it.hasNext()) {
//			states = states + " " + it.next();
//		}
//		model.addAttribute("states", states);
//	
//		return "location_find";
//	}
	
	@ModelAttribute("role")
	public Role getRole() {
		return new Role();
	}

	@ModelAttribute("roleWrapper")
	public RoleWrapper getRoleWrapper() {
		return new RoleWrapper();
	}
	
	@ModelAttribute(value="roleOptions")
	public List<String> getRoles() {
		return  roleRepository.findAllToListString();
	}

	@ModelAttribute(value="accountOptions")
	public List<String> getAccounts() {
		return  accountRepository.findAllToListString();
	}
	
	// ------------------------------ Validator ------------------------------

		//registering the LocationValidator with this controller using a WebDataBinder object.
//		@InitBinder
//		public void initBinder(WebDataBinder binder) {
//			binder.addValidators(new LocationValidator());
//		}

	
}