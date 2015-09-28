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
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Album;
import com.thoughtriott.metaplay.data.services.AlbumService;
import com.thoughtriott.metaplay.data.services.ArtistService;
import com.thoughtriott.metaplay.data.services.TrackService;
import com.thoughtriott.metaplay.data.wrappers.CreateAlbumWrapper;
import com.thoughtriott.metaplay.data.wrappers.CreateTrackWrapper;

@Controller
@RequestMapping("/album")
public class AlbumController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private AlbumService albumService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private TrackService trackService;
	
	@RequestMapping("/add")
	public String addAlbum(){
		return "album_add";
	}
	
	@RequestMapping("/review")
	public String review(HttpSession session, @Valid @ModelAttribute CreateAlbumWrapper createAlbumWrapper, Errors errors) {
		System.out.println("Invoking review() in AlbumController");
		if(errors.hasErrors()) {
			return "album_add";
		}
		session.setAttribute("createAlbumWrapper", createAlbumWrapper);
		return "album_review";
	}
	
	@RequestMapping("/save")
	public String saveAlbum(HttpSession session, SessionStatus status) {
		System.out.println("Invoking the saveAlbum() from AlbumController.");
		Album futureAlbum = new Album();
		CreateAlbumWrapper caw = (CreateAlbumWrapper) session.getAttribute("createAlbumWrapper");
//
//		String trackName = ctw.getName();
//		futureTrack.setName(trackName);
//		
//		int seconds = ctw.getLengthSeconds();
//		int minutes = ctw.getLengthMinutes();
//		futureTrack.setLengthMinSec(minutes, seconds);
//		
//		String lyrics = ctw.getLyrics();
//		futureTrack.setLyrics(lyrics);
//		
//		int trackBpm = ctw.getBpm();
//		futureTrack.setBpm(trackBpm);
//		int trackNumber = ctw.getTrackNumber();
//		futureTrack.setTrackNumber(trackNumber);
//		
//		System.out.println("Setting/Creating an Album");
//		
//		if(ctw.getAlbumFromList()!="** New Album **" && albumService.findAlbumByName(ctw.getAlbumFromList())!=null) {
//			futureTrack.setAlbum(albumService.findAlbumByName(ctw.getAlbumFromList()));
//		} else {
//				Album a = new Album();
//				a.setName(ctw.getTheNewAlbum());
//				//to create a new album, an artist isn't necessary... but if it does exist, add the artist to the album.
//				if(ctw.getArtistFromList()!="** New Artist **" && artistService.findArtistByName(ctw.getArtistFromList())!=null) {
//					a.setArtist(artistService.findArtistByName(ctw.getArtistFromList()));
//				} else if(ctw.getArtistFromList().equals("** New Artist **")) {
//					a.setArtist(artistService.createArtist(ctw.getTheNewArtist()));
//				}
//				futureTrack.setAlbum(albumService.createAlbum(a));
//		}
//		
		System.out.println("Creating the Album");
		albumService.createAlbum(futureAlbum);
		status.setComplete();
		return "redirect:/album/add";
	}
	
	@RequestMapping("/find")
	public String findAlbum(){
		return "404";
	}
	
	//Model Attributes ----------------------------------------
	
	@ModelAttribute("createAlbumWrapper")
	public CreateAlbumWrapper getCreateAlbumWrapper() {
		return new CreateAlbumWrapper();
	}
	
	@ModelAttribute(value="artistOptions")
	public List<String> getArtists() {
		return  artistService.findAllAsListString();
	}
	
}
