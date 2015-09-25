package com.thoughtriott.metaplay.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Album;
import com.thoughtriott.metaplay.data.entities.Track;
import com.thoughtriott.metaplay.data.services.AlbumService;
import com.thoughtriott.metaplay.data.services.ArtistService;
import com.thoughtriott.metaplay.data.services.TrackService;
import com.thoughtriott.metaplay.data.wrappers.CreateTrackWrapper;

@Controller
@RequestMapping("/track")
@SessionAttributes("createTrackWrapper")
public class TrackController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private AlbumService albumService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private TrackService trackService;

	@RequestMapping("/add")
	public String addTrack() {
		return "track_add";
	}
	
	@RequestMapping("/review")
	public String review(HttpSession session, @Valid @ModelAttribute CreateTrackWrapper createTrackWrapper, Errors errors) {
		System.out.println("Invoking review() in TrackController");
		if(errors.hasErrors()) {
			return "track_add";
		}
		session.setAttribute("createTrackWrapper", createTrackWrapper);
		return "track_review";
	}
	
	@RequestMapping("/save")
	public String saveTrack(HttpSession session, SessionStatus status) {
		System.out.println("Invoking the saveTrack() from TrackController.");
		Track futureTrack = new Track();
		CreateTrackWrapper ctw = (CreateTrackWrapper) session.getAttribute("createTrackWrapper");

		String trackName = ctw.getName();
		futureTrack.setName(trackName);
		
		int seconds = ctw.getLengthSeconds();
		int minutes = ctw.getLengthMinutes();
		futureTrack.setLengthMinSec(minutes, seconds);
		
		String lyrics = ctw.getLyrics();
		futureTrack.setLyrics(lyrics);
		
		int trackBpm = ctw.getBpm();
		futureTrack.setBpm(trackBpm);
		int trackNumber = ctw.getTrackNumber();
		futureTrack.setTrackNumber(trackNumber);
		
		System.out.println("Setting/Creating an Album");
		
		if(albumService.findAlbumByName(ctw.getAlbumFromList())!=null && ctw.getAlbumFromList()!="** New Artist **,") {
			futureTrack.setAlbum(albumService.findAlbumByName(ctw.getAlbumFromList()));
		} else {
				Album a = new Album();
				a.setName(ctw.getTheNewAlbum());
				//to create a new album, an artist isn't necessary... but if it does exist, add the artist to the album.
				if(artistService.findArtistByName(ctw.getArtistFromList())!=null && ctw.getArtistFromList()!="** New Artist **,") {
					a.setArtist(artistService.findArtistByName(ctw.getArtistFromList()));
				} else if (artistService.findArtistByName(ctw.getTheNewArtist())!=null) {
					a.setArtist(artistService.findArtistByName(ctw.getTheNewArtist()));
				}
				albumService.createAlbum(a);
				futureTrack.setAlbum(albumService.findAlbumByName(ctw.getTheNewAlbum()));
		}
		
		
		
		System.out.println("Creating the Track");
		trackService.createTrack(futureTrack);
		status.setComplete();
		return "redirect:/track/add";
	}
	
	@RequestMapping("/find")
	public String findTrack() {
		//implement findArtist method
		// return "artists"
		return "404";
	}
	
// ------------------------------ Validator ------------------------------

	//registering the ArtistValidator with this controller using a WebDataBinder object.
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(new CreateArtistWrapperValidator());
//	}
	
// ------------------------------ Model Attributes ------------------------------
	@ModelAttribute("createTrackWrapper")
	public CreateTrackWrapper getCreateTrackWrapper() {
		return new CreateTrackWrapper();
	}
	
	@ModelAttribute(value="artistOptions")
	public List<String> getArtists() {
		return  artistService.findAllAsListString();
	}
	
	@ModelAttribute(value="albumOptions")
	public List<String> getAlbums() {
		return  albumService.findAllAsListString();
	}

}
