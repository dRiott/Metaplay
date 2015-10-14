package com.thoughtriott.metaplay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtriott.metaplay.data.entities.Album;
import com.thoughtriott.metaplay.data.entities.Artist;
import com.thoughtriott.metaplay.data.entities.Track;
import com.thoughtriott.metaplay.data.repositories.jpa.AlbumRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.ArtistRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.TrackRepository;
import com.thoughtriott.metaplay.data.wrappers.AmazonService;
import com.thoughtriott.metaplay.data.wrappers.CreateTrackWrapper;

@Controller
@RequestMapping("/track")
public class TrackController extends AmazonService {

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
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveTrack(@ModelAttribute CreateTrackWrapper ctw) {
		System.out.println("Invoking the saveTrack() from TrackController.");
		Track futureTrack = new Track();
		futureTrack.setName(ctw.getName());
		futureTrack.setLengthMinSec(ctw.getLengthMinutes(), ctw.getLengthSeconds());
		futureTrack.setLyrics(ctw.getLyrics());
		
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
		Track persistedTrack = trackRepository.saveAndFlush(futureTrack);
		String id = ""+persistedTrack.getId();
		saveAudioFile(ctw.getMp3(), id, persistedTrack.getName());
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
