package com.thoughtriott.metaplay.controllers;

import com.thoughtriott.metaplay.data.entities.Location;
import com.thoughtriott.metaplay.data.wrappers.RepositoryKeeper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/location")
@SessionAttributes("location")
public class LocationController extends RepositoryKeeper {

	@RequestMapping("/add")
	public String addLocation(Model model){
		return "location_add";
	}
	
	@RequestMapping("/review")
	public String reviewLocation(@Valid @ModelAttribute Location location, Errors errors, HttpSession session) {
		System.out.println("LocationController - reviewLocation(): invoking");
		if(errors.hasErrors()) {
			return "location_add";
		}
		
		session.setAttribute("location", location);
		return "location_review";
	}
	
	@RequestMapping(value="/save")
	public String saveLocation(HttpSession session, SessionStatus status){
		System.out.println("LocationController - saveLocation(): invoking");
		Location location = (Location) session.getAttribute("location");
		String city = location.getCity();
		String state = location.getState();
		String country = location.getCountry();
		
		if(country.equals("United States")) {
			if(locationRepository.findLocationByCityAndState(city, state)!=null) {
				System.out.println("Location Controller: locationService.findLocation() US city/state exists... returning to Location/add.");
			} else {
				//united states: new city/state combo
				locationRepository.saveAndFlush(location);
			}
		} else {
			//country isn't US, setting state to null
			if(locationRepository.findLocationByCityAndCountry(city, country)!=null) {
				System.out.println("Location Controller: locationService.findLocation() NON-US city/country exists... returning to Location/add.");
			} else {
				//nons-US: new city/country combo
				location.setState(null);
				location.setCountry(location.getNewCountry());
				locationRepository.saveAndFlush(location);
			}
		}
		
		status.setComplete();
		return "redirect:/location/add";
	}
	
	// ------------------------------ Model Attributes ------------------------------

	@ModelAttribute("location")
	public Location getLocation() {
		return new Location();
	}

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
	
	@ModelAttribute(value="countryOptions")
	public List<String> getCountries() {
		return locationRepository.findAllCountriesToListString();
	}
	

	// ------------------------------ Validator ------------------------------
	/*//registering the LocationValidator with this controller using a WebDataBinder object.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new LocationValidator());
	}*/

}
