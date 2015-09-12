package com.thoughtriott.metaplay.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Artist;
import com.thoughtriott.metaplay.data.entities.Genre;
import com.thoughtriott.metaplay.data.entities.Location;
import com.thoughtriott.metaplay.data.entities.RecordLabel;
import com.thoughtriott.metaplay.data.services.ArtistService;

@Controller
@RequestMapping("/artist")
@SessionAttributes("artist")
public class ArtistController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ArtistService artistService;
	
	@RequestMapping("/add")
	public String addArtist(Model model) {
		System.out.println("Adding a new Artist to the model with the @ModelAttribute annotation.");
		return "artist_add";
	}
	
//	@RequestMapping("/add")
//	public String addLocation(Model model){
//		
//		//"location" attribute - STORES LOCATION TO BE EVENTUALLY PERSISTED
//		model.addAttribute("location", new Location());
//	
//		return "location_add";
//	}
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Artist artist, @ModelAttribute Location location, @ModelAttribute Genre genre, @ModelAttribute RecordLabel recordLabel, HttpSession session) {
		System.out.println("Invoking review() in ArtistController");
		System.out.println(artist.getName());
		session.setAttribute("artist", artist);
		session.setAttribute("location", location);
//		model.addAttribute(artist);
		return "artist_review";
	}
	
	@RequestMapping("/save")
	public String saveArtist(@ModelAttribute Artist artist, @ModelAttribute Location location, SessionStatus status, @ModelAttribute Genre genre, @ModelAttribute RecordLabel recordLabel, HttpSession session) {
		System.out.println("Invoking the saveArtist() from ArtistController.");
		Artist a = (Artist) session.getAttribute("artist");
		Location l = (Location) session.getAttribute("location");
		Genre g = (Genre) session.getAttribute("genre");
		RecordLabel rl = (RecordLabel) session.getAttribute("recordLabel");
		int locationId = l.getId();
		int recordLabelId = rl.getId();
		int genreId = g.getId();
		
		artistService.createArtist(a.getName(), genreId, locationId, recordLabelId, a.getBiography());
		
		//setComplete wipes the session of the info that we passed to the review page
		//so that when we redirect to the /artist/add page, a blank form is displayed.
		status.setComplete();
		return "redirect:/artist/add";
	}
	
	@RequestMapping("/find")
	public String findArtist() {
		//implement findArtist method
		// return "artists"
		return "404";
	}
	
	// Adds the Artist to the Model with the @ModelAttribute annotation
	@ModelAttribute("artist")
	public Artist getArtist() {
		return new Artist();
	}
	
	// Adds the Location to the Model with the @ModelAttribute annotation
	@ModelAttribute("location")
	public Location getLocation() {
		return new Location();
	}
	
	// Adds the Location to the Model with the @ModelAttribute annotation
	@ModelAttribute("genre")
	public Genre getGenre() {
		return new Genre();
	}
	
	// Adds the Location to the Model with the @ModelAttribute annotation
	@ModelAttribute("recordLabel")
	public RecordLabel getRecordLabel() {
		return new RecordLabel();
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
