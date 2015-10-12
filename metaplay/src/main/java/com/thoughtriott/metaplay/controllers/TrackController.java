package com.thoughtriott.metaplay.controllers;

import java.util.List;

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
import com.thoughtriott.metaplay.data.entities.Artist;
import com.thoughtriott.metaplay.data.entities.Track;
import com.thoughtriott.metaplay.data.repositories.AlbumRepository;
import com.thoughtriott.metaplay.data.repositories.ArtistRepository;
import com.thoughtriott.metaplay.data.repositories.TrackRepository;
import com.thoughtriott.metaplay.data.wrappers.CreateTrackWrapper;

@Controller
@RequestMapping("/track")
@SessionAttributes("createTrackWrapper")
public class TrackController {

	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private TrackRepository trackRepository;

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
		
		if(!ctw.getAlbumFromList().equals("** New Album **") && !ctw.getAlbumFromList().equals("** Do Not Add Album Now **") && albumRepository.findAlbumByName(ctw.getAlbumFromList())!=null) {
			System.out.println("Hmm, inside the first if");
			futureTrack.setAlbum(albumRepository.findAlbumByName(ctw.getAlbumFromList()).get(0));
		} else if (ctw.getAlbumFromList().equals("** New Album **")){
				Album newAlbum = new Album();
				newAlbum.setName(ctw.getTheNewAlbum());
				//to create a new album, an artist isn't necessary... but if it does exist, add the artist to the album.
				if(ctw.getArtistFromList()!="** New Artist **" && artistRepository.findArtistByName(ctw.getArtistFromList())!=null) {
					newAlbum.setArtist(artistRepository.findArtistByName(ctw.getArtistFromList()));
				} else if(ctw.getArtistFromList().equals("** New Artist **")) {
					newAlbum.setArtist(artistRepository.saveAndFlush(new Artist(ctw.getTheNewArtist())));
				}
				futureTrack.setAlbum(albumRepository.saveAndFlush(newAlbum));
		}
		
		System.out.println("Creating the Track");
		trackRepository.saveAndFlush(futureTrack);
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
		return  artistRepository.findAllToListString();
	}
	
	@ModelAttribute(value="albumOptions")
	public List<String> getAlbums() {
		return  albumRepository.findAllToListString();
	}

}
