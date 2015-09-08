package com.thoughtriott.metaplay.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Artist;

@Controller
@RequestMapping("/artist")
@SessionAttributes("artist")
public class ArtistController {

	@RequestMapping("/add")
	public String addArtist(Model model) {
		System.out.println("Adding a new Artist to the model with the @ModelAttribute annotation.");
		return "artist_add";
	}
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Artist artist) {
		System.out.println("Invoking review() in ArtistController");
		return "artist_review";
	}
	
	@RequestMapping("/save")
	public String saveArtist(@ModelAttribute Artist artist, SessionStatus status) {
		System.out.println("Invoking the saveArtist() from ArtistController.");
		System.out.println(artist);
		//setComplete wipes the session of the info that we passed to the review page
		//so that when we redirect to the /artist/add page, a blank form is displayed.
		status.setComplete();
		return "redirect:/artist/add";
	}
	
	// Adds the Artist to the Model with the @ModelAttribute annotation
	@ModelAttribute("artist")
	public Artist getArtist() {
		return new Artist();
	}
	
	//@ModelAttribute annotation tells spring that this method 
	// should have its return value placed within the model with key "locationOptions"
	@ModelAttribute(value="locationOptions")
	public List<String> getLocations() {
		return new LinkedList<>(Arrays.asList(new String[] { "Seattle", "Los Angeles", "Denver",
				"San Francisco", "Chicago", "Atlanta", "Dallas", "Portland", "Other" }));
	}
	
	// place "genreOptions" List in the model to be used on our artist_add.jsp page
	@ModelAttribute(value="genreOptions")
	public List<String> getGenres() {
		return  new LinkedList<>(Arrays.asList(new String[] { "Blues", "Rock", "Juke",
				"D&B", "Jazz Hop", "Hip Hop", "BasedGod", "Drugs", "Other" }));
	}
}
