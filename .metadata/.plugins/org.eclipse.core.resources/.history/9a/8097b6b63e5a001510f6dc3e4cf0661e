package com.thoughtriott.metaplay.controllers;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Location;
import com.thoughtriott.metaplay.data.services.LocationService;

@Controller
@RequestMapping("/location")
public class LocationController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping("/add")
	public String addLocation(Model model){
		
		//"location" attribute - STORES LOCATION TO BE EVENTUALLY PERSISTED
		model.addAttribute("location", new Location());
	
		return "location_add";
	}
	
	//allows user to confirm the new information
	@RequestMapping("/review")
	public String reviewLocation(@ModelAttribute Location location, Model model, HttpSession session) {
		System.out.println("Invoking reviewLocation() in LocationController");
		System.out.println(location.getCity());
		session.setAttribute("location", location);
		model.addAttribute(location);
		return "location_review";
	}
	
	//creates a new location!
	@RequestMapping(value="/save")
	public String saveLocation(@ModelAttribute Location location, HttpSession session, SessionStatus status){
		System.out.println("invoking saveLocation");
		Location l = (Location) session.getAttribute("location");
		locationService.createLocation(l.getCity(), l.getState());
		
		//setComplete wipes the session info passed to the review page when redirect to black form is desired
		status.setComplete();
		return "redirect:/location/add";
	}
	
	@RequestMapping("/find")
	public String findLocation(Model model){
		
		//"denver" attribute
		Location denver = locationService.findLocation("Denver", "Colorado");
		model.addAttribute("currentLocation", denver);

		//"all" attribute
		String all = locationService.findAllAsString();
		model.addAttribute("all", all);

		//"states" attribute
		Iterator<String> it = locationService.findDistinctStates().iterator();
		String states = "";
		while(it.hasNext()) {
			states = states + " " + it.next();
		}
		model.addAttribute("states", states);
	
		return "location_find";
	}

}
