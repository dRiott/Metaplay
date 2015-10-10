package com.thoughtriott.metaplay.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.thoughtriott.metaplay.data.repositories.AlbumRepository;
import com.thoughtriott.metaplay.data.repositories.ArtistRepository;
import com.thoughtriott.metaplay.data.repositories.LocationRepository;
import com.thoughtriott.metaplay.data.repositories.RecordLabelRepository;
import com.thoughtriott.metaplay.data.repositories.TrackRepository;
import com.thoughtriott.metaplay.data.wrappers.CreateAlbumWrapper;
import com.thoughtriott.metaplay.data.wrappers.CreateTrackWrapper;
import com.thoughtriott.metaplay.utilities.DateFormatter;

@Controller
@RequestMapping("/album")
@SessionAttributes("createAlbumWrapper")
public class AlbumController {

	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private RecordLabelRepository recordLabelRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private TrackRepository trackRepository;
	@Autowired
	private DateFormatter dateFormatter;
	
	@RequestMapping("/add")
	public String addAlbum(){
		return "album_add";
	}
	
	@RequestMapping("/review")
	public String review(HttpSession session, @Valid @ModelAttribute CreateAlbumWrapper createAlbumWrapper, Errors errors) {
		System.out.println("AlbumController: invoking review() ");
		if(errors.hasErrors()) {
			return "album_add";
		}
		session.setAttribute("createAlbumWrapper", createAlbumWrapper);
		return "album_review";
	}
	
	@RequestMapping("/save")
	public String saveAlbum(HttpSession session, SessionStatus status) {
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BEGIN ALBUM PERSISTENCE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println("\n ************************************ \nAlbumController: invoking saveAlbum()");
		Album futureAlbum = new Album();
		CreateAlbumWrapper caw = (CreateAlbumWrapper) session.getAttribute("createAlbumWrapper");

		System.out.println("AlbumController: setName() - \"" + caw.getName() + "\"");
		futureAlbum.setName(caw.getName());
		
		int seconds = caw.getLengthSeconds();
		int minutes = caw.getLengthMinutes();
		futureAlbum.setLengthMinSec(minutes, seconds);

		Date albumReleaseDate = dateFormatter.getDateFromString(caw.getReleaseDate()); 
		futureAlbum.setReleaseDate(albumReleaseDate);
		
		
		// ****************** BEGIN TRACK PERSISTENCE ******************
		Map<Integer, String> tracksMap = new HashMap<Integer, String>();
		
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
				System.out.println("AlbumController: while(createTrackWrappersList.hasNext()) - About to setTrackNumber: " + counter);
				newTrack.setTrackNumber(counter);
				newTrack.setName(ctw.getName());
				newTrack.setBpm(ctw.getBpm());
				System.out.println("AlbumController: while(createTrackWrappersList.hasNext()) -  **** Hey, I've got this Track: " + newTrack.toString());
				
				System.out.println("AlbumController: while(createTrackWrappersList.hasNext()) - About to add the track to the album...");
				if (trackRepository.findTrackByName(ctw.getName())!=null) {
					System.out.println("AlbumController: while(createTrackWrappersList.hasNext()) - Found the track already in the DB: " + ctw.getName());
					futureAlbum.addTrack(trackRepository.findTrackByName(ctw.getName()).get(0));
				} else {
					System.out.println("AlbumController: while(createTrackWrappersList.hasNext()) - No track found in the DB. Creating one...");
					futureAlbum.addTrack(trackRepository.saveAndFlush(newTrack));
				}
				
				//adding Tracks to Map<Integer, String> tracksMap
				tracksMap.put(newTrack.getLength(), newTrack.getName());
				
				System.out.println("AlbumController: while(createTrackWrappersList.hasNext()) - ******** Done with this Track: " + newTrack.getName() + " ********" );
			}
		}
		System.out.println("Total tracks: " + counter);
		futureAlbum.setNumTracks(counter);
		// ****************** END TRACK PERSISTENCE ******************	
		
		// ****************** BEGIN ARTIST PERSISTENCE ******************	
		System.out.println("AlbumController: Setting/Creating an Artist");
		if(caw.getArtistFromList()!="** New Artist **" && artistRepository.findArtistByName(caw.getArtistFromList())!=null) {
			System.out.println("AlbumController: setting Artist - Found the artist already in the DB: " + caw.getArtistFromList());
			futureAlbum.setArtist(artistRepository.findArtistByName(caw.getArtistFromList()));
		} else {
			//Creating a new Artist...
			Artist a = new Artist();
			a.setName(caw.getTheNewArtist());
			System.out.println("AlbumController: setting Artist -  Artist didn't exist in the DB. A new one is about to be created: " + a.toString());
			futureAlbum.setArtist(artistRepository.saveAndFlush(a));
		}
		// ****************** END ARTIST PERSISTENCE ******************
		
		// ****************** BEGIN RECORD LABEL PERSISTENCE ******************
		System.out.println("AlbumController: Setting/Creating a Record Label");
		String recordLabelName = caw.getRecordLabelFromList();
		String recordLabelCity = caw.getRecordLabelCity();
		String recordLabelState = caw.getRecordLabelState();
		if(recordLabelName!="** New Record Label **" && recordLabelRepository.findRecordLabelByName(recordLabelName)!=null) {
			futureAlbum.setRecordLabel(recordLabelRepository.findRecordLabelByName(recordLabelName).get(0));
		} else if(recordLabelName.equals("** New Record Label **")) {
			String newRecordLabelName = caw.getTheNewRecordLabel();
			RecordLabel rl = (RecordLabel) recordLabelRepository.saveAndFlush(new RecordLabel(newRecordLabelName, locationRepository.findLocationByCityAndState(recordLabelCity, recordLabelState)));
			futureAlbum.setRecordLabel(rl);
		}
		// ****************** END RECORD LABEL PERSISTENCE ******************
		
		System.out.println("AlbumController: final step - Creating the Album");
		Album persistedAlbum = albumRepository.saveAndFlush(futureAlbum);
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END ALBUM PERSISTENCE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		

		// ****************** BEGIN TRACK SET ALBUM TO NEW ALBUM ******************
		Iterator<Map.Entry<Integer, String>> trackEntries = tracksMap.entrySet().iterator();
		while (trackEntries.hasNext()) {
		    Map.Entry<Integer, String> trackEntry = trackEntries.next();
		    Track trackToUpdate = trackRepository.findTrackByNameAndLength(trackEntry.getValue(), trackEntry.getKey()).get(0);
		    trackToUpdate.setAlbum(persistedAlbum);
		    trackRepository.saveAndFlush(trackToUpdate);
		}
		// ****************** END TRACK SET ALBUM TO NEW ALBUM ******************

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
		return  artistRepository.findAllToListString();
	}
	
	
	@ModelAttribute(value="recordLabelOptions")
	public List<String> getRecordLabels() {
		return  recordLabelRepository.findAllAsListString();
	}
	
}
