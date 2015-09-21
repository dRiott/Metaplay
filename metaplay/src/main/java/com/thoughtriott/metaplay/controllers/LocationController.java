package com.thoughtriott.metaplay.controllers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
		return "location_add";
	}
	
	@RequestMapping("/review")
	public String reviewLocation(@Valid @ModelAttribute Location location, Errors errors, Model model, HttpSession session) {
		System.out.println("Invoking reviewLocation() in LocationController");
		if(errors.hasErrors()) {
			return "location_add";
		}
		session.setAttribute("location", location);
		model.addAttribute(location);
		return "location_review";
	}
	
	@RequestMapping(value="/save")
	public String saveLocation(@ModelAttribute Location location, HttpSession session, SessionStatus status){
		System.out.println("invoking saveLocation");
		Location l = (Location) session.getAttribute("location");
		locationService.createLocation(l.getCity(), l.getState());
		status.setComplete();
		return "redirect:/location/add";
	}
	
	@RequestMapping("/find")
	public String findLocation(Model model){
		
		//"denver" attribute
		Location portland = locationService.findLocation("Portland", "Oregon");
		model.addAttribute("currentLocation", portland);

		//"all" attribute
		String all = locationService.findAllAsString();
		model.addAttribute("all", all);

		//"states" attribute
		Iterator<String> it = locationService.findDistinctStatesToString().iterator();
		String states = "";
		while(it.hasNext()) {
			states = states + " " + it.next();
		}
		model.addAttribute("states", states);
	
		return "location_find";
	}

	
	//adds "stateOptions" List to the model
	@ModelAttribute("stateOptions")
	public List<String> getTypes () {
		return new LinkedList<>(Arrays.asList(new String[] { 
		"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado","Connecticut", 
		"Delaware", "District Of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
		"Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
		"Mississippi", "Missouri", "Montana Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
		"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania","Rhode Island","South Carolina",
		"South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington", "West Virginia", "Wisconsin", "Wyoming"
		}));
	}	
	
// ------------------------------ Validator ------------------------------

	//registering the LocationValidator with this controller using a WebDataBinder object.
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(new LocationValidator());
//	}
		
	@ModelAttribute("location")
	public Location getLocation() {
		return new Location();
	}
	
}
