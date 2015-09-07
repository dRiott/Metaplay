package com.thoughtriott.metaplay.controllers;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thoughtriott.metaplay.data.entities.Artist;

@Controller
@RequestMapping("/artist")
public class ArtistController {

	@RequestMapping("/add")
	public String addArtist(Model model) {
		System.out.println("Adding a new Artist to the model with the @ModelAttribute annotation.");
		return "artist_add";
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

	@RequestMapping("/save")
	public String saveArtist(@ModelAttribute Artist artist) {
		System.out.println("Invoking the saveArtist() from ArtistController.");
		System.out.println(artist);
		return "added";
	}
}
