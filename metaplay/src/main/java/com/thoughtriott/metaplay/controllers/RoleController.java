package com.thoughtriott.metaplay.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.entities.Role;
import com.thoughtriott.metaplay.data.wrappers.RepositoryKeeper;
import com.thoughtriott.metaplay.data.wrappers.RoleWrapper;

@Controller
@RequestMapping("/role")
public class RoleController extends RepositoryKeeper {

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
		Account account = accountRepository.findAccountByAccountname(roleWrapper.getAccountname()).get(0);
		Role role = roleRepository.findRoleOrderByName(roleWrapper.getRoleName()).get(0);
		String action = roleWrapper.getActionOption();
		if(action.equals("Add")) {
		accountRepository.saveAndFlush(account.addRole(role));
		} else if(action.equals("Remove")) {
			accountRepository.saveAndFlush(account.removeRole(role));
		}
		return "redirect:/role/assign";
	}
	
	// ------------------------------ Model Attributes ------------------------------
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
	
	@ModelAttribute(value="actionOptions")
	public List<String> getActionOptions() {
		List<String> actionOptions = new ArrayList<>();
		actionOptions.add("Add");
		actionOptions.add("Remove");
		return actionOptions;
	}

	@ModelAttribute(value="accountOptions")
	public List<String> getAccounts() {
		return  accountRepository.findAllToListString();
	}
	
	// ------------------------------ Validator ------------------------------
	/*registering the LocationValidator with this controller using a WebDataBinder object.
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			binder.addValidators(new LocationValidator());
		}*/

}
