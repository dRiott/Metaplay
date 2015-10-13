package com.thoughtriott.metaplay.data.repositories.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.Role;

public class RoleRepositoryImpl implements RoleRepositoryCustom {
	
	@Autowired
	RoleRepository roleRepository;
	
	//returns List<String> of each Role
		@Override	
		public List<String> findAllToListString() {
			List<Role> roleList = roleRepository.findAll();
			Iterator<Role> it = roleList.iterator();
			List<String> roleStringList = new ArrayList<String>();
			while (it.hasNext()) {
				Role role = it.next();
				roleStringList.add(role.getName());
			}
			if (roleList.size() == 0) {
				System.out.println("The results list was empty.");
				roleStringList.add("No Roles exist, add one!");
				roleStringList.add("** New Role **");
				return roleStringList;
			} else {
				roleStringList.add("** New Role **");
				return roleStringList;
			}
		}

}
