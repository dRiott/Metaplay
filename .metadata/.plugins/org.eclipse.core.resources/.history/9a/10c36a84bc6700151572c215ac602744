package com.thoughtriott.metaplay.controllers;

import java.util.Date;
import java.util.Iterator;
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
import com.thoughtriott.metaplay.data.entities.Artist;
import com.thoughtriott.metaplay.data.entities.RecordLabel;
import com.thoughtriott.metaplay.data.entities.Track;
import com.thoughtriott.metaplay.data.services.AlbumService;
import com.thoughtriott.metaplay.data.services.ArtistService;
import com.thoughtriott.metaplay.data.services.LocationService;
import com.thoughtriott.metaplay.data.services.RecordLabelService;
import com.thoughtriott.metaplay.data.wrappers.CreateAlbumWrapper;
import com.thoughtriott.metaplay.utilities.DateFormatter;

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
	private RecordLabelService recordLabelService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private DateFormatter dateFormatter;
	
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

		String albumName = caw.getName();
		futureAlbum.setName(albumName);
		
		int seconds = caw.getLengthSeconds();
		int minutes = caw.getLengthMinutes();
		futureAlbum.setLengthMinSec(minutes, seconds);

		Date albumReleaseDate = dateFormatter.getDateFromString(caw.getReleaseDate()); 
		futureAlbum.setReleaseDate(albumReleaseDate);
		
		List<Track> tracks = caw.getTracks();
		Iterator<Track> it = tracks.iterator();
		int counter=0;
		while(it.hasNext() && !it.next().getName().equals("")) {
			Track t = it.next();

			if(!t.getMinutes().equals("") && !t.getSeconds().equals("")) {
				t.setLengthFromStringMinSec(t.getMinutes(), t.getSeconds());
			}
			counter++;
			t.setTrackNumber(counter);
			futureAlbum.addTrack((Track) it.next());
		}
		
		//		Setting/Creating an Album
		System.out.println("Setting/Creating an Artist");
		if(caw.getArtistFromList()!="** New Album **" && albumService.findAlbumByName(caw.getArtistFromList())!=null) {
			futureAlbum.setArtist(artistService.findArtistByName(caw.getArtistFromList()));
		} else {
			//Creating a new Artist...
			Artist a = new Artist();
			a.setName(caw.getTheNewArtist());
			futureAlbum.setArtist(artistService.createArtist(a));
		}
		
		futureAlbum.setNumTracks(counter);
		
		// 		Setting/Creating a Record Label
		System.out.println("Setting/Creating a Record Label");
		String recordLabelName = caw.getRecordLabelFromList();
		String recordLabelCity = caw.getRecordLabelCity();
		String recordLabelState = caw.getRecordLabelState();
		if(recordLabelName!="** New Record Label **" && recordLabelService.findRecordLabelByName(recordLabelName)!=null ) {
			futureAlbum.setRecordLabel(recordLabelService.findRecordLabelByName(recordLabelName));
		} else if(recordLabelName.equals("** New Record Label **")) {
			String newRecordLabelName = caw.getTheNewRecordLabel();
			RecordLabel rl = (RecordLabel) recordLabelService.createRecordLabel(newRecordLabelName, locationService.findLocation(recordLabelCity, recordLabelState));
			futureAlbum.setRecordLabel(rl);
		}
		
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
