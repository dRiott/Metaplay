package com.thoughtriott.metaplay.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thoughtriott.metaplay.data.entities.Location;
import com.thoughtriott.metaplay.data.services.LocationService;

@Controller
public class HomeController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping("/home")
	public String goHome(Model model){
		
		Location denver = locationService.findLocation();
		
		model.addAttribute("currentLocation", denver);
		
		return "home";
	}
	
	@RequestMapping("/")
	public String goMain () {
		return "welcome";
	}
}
