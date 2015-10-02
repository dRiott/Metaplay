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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Album;
import com.thoughtriott.metaplay.data.entities.Artist;
import com.thoughtriott.metaplay.data.entities.RecordLabel;
import com.thoughtriott.metaplay.data.entities.Track;
import com.thoughtriott.metaplay.data.services.AlbumService;
import com.thoughtriott.metaplay.data.services.ArtistService;
import com.thoughtriott.metaplay.data.services.LocationService;
import com.thoughtriott.metaplay.data.services.RecordLabelService;
import com.thoughtriott.metaplay.data.services.TrackService;
import com.thoughtriott.metaplay.data.wrappers.CreateAlbumWrapper;
import com.thoughtriott.metaplay.data.wrappers.CreateTrackWrapper;
import com.thoughtriott.metaplay.utilities.DateFormatter;

@Controller
@RequestMapping("/album")
@SessionAttributes("createAlbumWrapper")
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
	private TrackService trackService;
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
		System.out.println("\n ************************************ \nInvoking the saveAlbum() from AlbumController.");
		Album futureAlbum = new Album();
		CreateAlbumWrapper caw = (CreateAlbumWrapper) session.getAttribute("createAlbumWrapper");

		System.out.println("Album name: " + caw.getName());
		futureAlbum.setName(caw.getName());
		
		int seconds = caw.getLengthSeconds();
		int minutes = caw.getLengthMinutes();
		futureAlbum.setLengthMinSec(minutes, seconds);

		Date albumReleaseDate = dateFormatter.getDateFromString(caw.getReleaseDate()); 
		futureAlbum.setReleaseDate(albumReleaseDate);
		
		List<CreateTrackWrapper> createTrackWrappers = caw.getCreateTrackWrappers();
		Iterator<CreateTrackWrapper> it = createTrackWrappers.iterator();
		int counter=0;
		while(it.hasNext()) {
			CreateTrackWrapper ctw = it.next();
			if(!ctw.getName().isEmpty()){
				System.out.println("Track name: " + ctw.getName());
				Track newTrack = new Track();
				if(ctw.getLengthMinutes()!=0 && ctw.getLengthSeconds()!=0) {
					newTrack.setLengthMinSec(ctw.getLengthMinutes(), ctw.getLengthSeconds());
				}
				counter++;
				System.out.println("About to setTrackNumber: " + counter);
				newTrack.setTrackNumber(counter);
				newTrack.setName(ctw.getName());
				newTrack.setBpm(ctw.getBpm());
				System.out.println("**** Hey, I've got this Track: " + newTrack.toString());
				
				System.out.println("About to add the track to the album...");
				if (trackService.findTrack(ctw.getName()) != null) {
					System.out.println("Found the track already in the DB: " + ctw.getName());
					futureAlbum.addTrack(trackService.findTrack(ctw.getName()));
				} else {
					System.out.println("No track found in the DB. Creating one...");
					futureAlbum.addTrack(trackService.createTrack(newTrack));
				}
				System.out.println("******** Done with this Track: " + newTrack.getName() + " ********" );
			}
		}
		
		System.out.println("Total tracks: " + counter);
		futureAlbum.setNumTracks(counter);
		
		//		Setting/Creating an Album
		System.out.println("Setting/Creating an Artist");
		if(caw.getArtistFromList()!="** New Artist **" && artistService.findArtistByName(caw.getArtistFromList())!=null) {
			System.out.println("Found the artist already in the DB: " + caw.getArtistFromList());
			futureAlbum.setArtist(artistService.findArtistByName(caw.getArtistFromList()));
		} else {
			//Creating a new Artist...
			Artist a = new Artist();
			a.setName(caw.getTheNewArtist());
			System.out.println("Artist didn't exist in the DB. A new one is about to be created: " + a.toString());
			futureAlbum.setArtist(artistService.createArtist(a));
		}
		
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
		Album createdAlbum = albumService.createAlbum(futureAlbum);

		
		// TRYING TO ADD THE ALBUM ID TO ALL THE TRACKS I JUST ADDED
//		int album_id = createdAlbum.getId();
//		
//		List<CreateTrackWrapper> createTrackWrappers2 = caw.getCreateTrackWrappers();
//		Iterator<CreateTrackWrapper> it2 = createTrackWrappers.iterator();
//		while(it2.hasNext()) {
//			CreateTrackWrapper ctw = it2.next();
//			if(!ctw.getName().isEmpty()){
//				Track t = trackService.findTrackByName(ctw.getName());
//				t.setAlbumId(album_id);
//			}
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
		System.out.println("Adding artistOptions to the model.. :" + artistService.findAllAsListString());
		return  artistService.findAllAsListString();
	}
	
	
	@ModelAttribute(value="recordLabelOptions")
	public List<String> getRecordLabels() {
		return  recordLabelService.findAllAsListString();
	}
	
}
