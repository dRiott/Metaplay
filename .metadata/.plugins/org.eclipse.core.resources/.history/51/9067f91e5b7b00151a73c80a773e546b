package com.thoughtriott.metaplay.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.thoughtriott.metaplay.data.wrappers.AmazonService;
import com.thoughtriott.metaplay.data.wrappers.CreateAlbumWrapper;
import com.thoughtriott.metaplay.data.wrappers.CreateTrackWrapper;

@Controller
@RequestMapping("/album")
@SessionAttributes("createAlbumWrapper")
public class AlbumController extends AmazonService {

	@RequestMapping("/add")
	public String addAlbum(){
		return "album_add";
	}
	
	@RequestMapping("/save")
	public String saveAlbum(@Valid @ModelAttribute CreateAlbumWrapper caw, Errors errors, SessionStatus status) {
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BEGIN ALBUM PERSISTENCE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println("AlbumController - saveAlbum(): invoking ");
		Album futureAlbum = new Album();
		if(caw.getName().isEmpty()) {
			futureAlbum = (albumRepository.findAlbumByName(caw.getAlbumFromList()).get(0));
			System.out.println("AlbumController: setName() - \"" + caw.getAlbumFromList() + "\"");
		} else {
			futureAlbum.setName(caw.getName());
			System.out.println("AlbumController: setName() - \"" + caw.getName() + "\"");
		}
		
		if(caw.getAlbumCover()!=null) {
			super.saveImage(caw.getAlbumCover(), ALBUM, caw.getName());
		}
		
		futureAlbum.setLengthMinSec(caw.getLengthMinutes(), caw.getLengthSeconds());
		futureAlbum.setReleaseDate(dateFormatter.getDateFromString(caw.getReleaseDate()));
		
		// ****************** BEGIN TRACK PERSISTENCE ******************
		Map<Integer, String> tracksMap = new HashMap<Integer, String>(); //for setting the tracks' album after creating THIS album
		
		List<CreateTrackWrapper> createTrackWrappers = caw.getCreateTrackWrappers();
		Iterator<CreateTrackWrapper> it = createTrackWrappers.iterator();
		int numTracksCounter=0;
		while(it.hasNext()) {
			CreateTrackWrapper ctw = it.next();
			if(!ctw.getName().isEmpty()){
				System.out.println("Track name: " + ctw.getName() + " - About to add the track to the album...");
				if (trackRepository.findTrackByName(ctw.getName()).size()>0) {
					//track already exists in the DB. Updating.
					System.out.println("AlbumController - TRACK PERSISTENCE: Found the track already in the DB: " + ctw.getName());
					Track foundTrack = trackRepository.findTrackByName(ctw.getName()).get(0);
					if(ctw.getLengthMinutes()!=0 & ctw.getLengthSeconds()!=0) {
						foundTrack.setLengthMinSec(ctw.getLengthMinutes(), ctw.getLengthSeconds());
					}
					if(ctw.getBpm()!=0) {
						foundTrack.setBpm(ctw.getBpm());
					}
					if(ctw.getMp3()!=null){
						saveAudioFile(ctw.getMp3(), ""+foundTrack.getId(), foundTrack.getName());
					}
					futureAlbum.addTrack(foundTrack);
				} else {
					//track doesn't exist in the DB. Creating.
					System.out.println("AlbumController - TRACK PERSISTENCE: No track found in the DB. Creating one...");
					Track newTrack = new Track();
					if(ctw.getLengthMinutes()!=0 & ctw.getLengthSeconds()!=0) {
						newTrack.setLengthMinSec(ctw.getLengthMinutes(), ctw.getLengthSeconds());
					}
					numTracksCounter++;
					System.out.println("AlbumController: TRACK PERSISTENCE, new Track - About to setTrackNumber: " + numTracksCounter);
					newTrack.setTrackNumber(numTracksCounter);
					newTrack.setName(ctw.getName());
					newTrack.setBpm(ctw.getBpm());
					System.out.println("AlbumController: TRACK PERSISTENCE **** Hey, I've got this Track: " + newTrack.toString());
					
					Track persistedTrack = trackRepository.saveAndFlush(newTrack);
					
					if(ctw.getMp3()!=null) {
						saveAudioFile(ctw.getMp3(), ""+persistedTrack.getId(), persistedTrack.getName());
					}
					
					futureAlbum.addTrack(persistedTrack);
					//adding Tracks to Map<Integer, String> tracksMap
					tracksMap.put(newTrack.getLength(), newTrack.getName());
					System.out.println("AlbumController: TRACK PERSISTENCE **** Done with this Track: " + newTrack.getName());
				}
			}
		}
		
		System.out.println("Total tracks: " + numTracksCounter);
		futureAlbum.setNumTracks(numTracksCounter);
		
		// ****************** BEGIN ARTIST PERSISTENCE ******************	
		System.out.println("AlbumController: Setting/Creating an Artist");
		if(caw.getArtistFromList()!="** New Artist **" && artistRepository.findArtistByName(caw.getArtistFromList())!=null) {
			System.out.println("AlbumController: setting Artist - Found the artist already in the DB: " + caw.getArtistFromList());
			futureAlbum.setArtist(artistRepository.findArtistByName(caw.getArtistFromList()));
		} else {
			//Creating a new Artist...
			Artist newArtist = new Artist();
			newArtist.setName(caw.getTheNewArtist());
			System.out.println("AlbumController: setting Artist -  Artist didn't exist in the DB, creating: " + newArtist.toString());
			futureAlbum.setArtist(artistRepository.saveAndFlush(newArtist));
		}
		
		// ****************** BEGIN RECORD LABEL PERSISTENCE ******************
		System.out.println("AlbumController: Setting/Creating a Record Label");
		String recordLabelName = caw.getRecordLabelFromList();
		String recordLabelCity = caw.getRecordLabelCity();
		String recordLabelState = caw.getRecordLabelState();
		if(recordLabelName!="** New Record Label **" && recordLabelRepository.findRecordLabelByName(recordLabelName).size()>0) {
			futureAlbum.setRecordLabel(recordLabelRepository.findRecordLabelByName(recordLabelName).get(0));
		} else if(recordLabelName.equals("** New Record Label **")) {
			String newRecordLabelName = caw.getTheNewRecordLabel();
			RecordLabel rl = (RecordLabel) recordLabelRepository.saveAndFlush(
				new RecordLabel(newRecordLabelName, locationRepository.findLocationByCityAndState(recordLabelCity, recordLabelState)));
			futureAlbum.setRecordLabel(rl);
		}
		
		System.out.println("AlbumController: final step - Creating the Album");
		Album persistedAlbum = albumRepository.saveAndFlush(futureAlbum);
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END ALBUM PERSISTENCE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		

		// ~~~~~~~~~~~~ CLEAN UP: BEGIN TRACK ALBUM SETTING TO THE NEWLY CREATED ALBUM  ~~~~~~~~~~~~
		Iterator<Map.Entry<Integer, String>> trackEntries = tracksMap.entrySet().iterator();
		while (trackEntries.hasNext()) {
		    Map.Entry<Integer, String> trackEntry = trackEntries.next();
		    Track trackToUpdate = trackRepository.findTrackByNameAndLength(trackEntry.getValue(), trackEntry.getKey()).get(0);
		    trackToUpdate.setAlbum(persistedAlbum);
		    trackRepository.saveAndFlush(trackToUpdate);
		}

		status.setComplete();
		return "redirect:/album/add";
	}
	
	// ------------------------------ Model Attributes ------------------------------
	@ModelAttribute("createAlbumWrapper")
	public CreateAlbumWrapper getCreateAlbumWrapper() {
		return new CreateAlbumWrapper();
	}
	
	@ModelAttribute(value="artistOptions")
	public List<String> getArtists() {
		return  artistRepository.findAllToListString();
	}
	
	@ModelAttribute(value="albumOptions")
	public List<String> getAlbums() {
		return  albumRepository.findAllToListString();
	}
	
	@ModelAttribute(value="recordLabelOptions")
	public List<String> getRecordLabels() {
		return  recordLabelRepository.findAllAsListString();
	}
	
	@ModelAttribute("recordLabelStateOptions")
	public List<String> getTypes () {
		return new LinkedList<>(Arrays.asList(new String[] { 
		"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado","Connecticut", 
		"Delaware", "District Of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
		"Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
		"Mississippi", "Missouri", "Montana Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
		"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania","Rhode Island","South Carolina",
		"South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington", "West Virginia", "Wisconsin", "Wyoming"
		}));
	}	
	
	@ModelAttribute(value="recordLabelCountryOptions")
	public List<String> getCountries() {
		return locationRepository.findAllCountriesToListString();
	}
	
}

	// ------------------------------ Notes / Old Code ------------------------------
	
		/*@RequestMapping("/review")
		public String review(HttpSession session, @Valid @ModelAttribute CreateAlbumWrapper createAlbumWrapper, Errors errors) {
			System.out.println("AlbumController: invoking review() ");
			if(errors.hasErrors()) {
				return "album_add";
			}
			session.setAttribute("createAlbumWrapper", createAlbumWrapper);
			return "album_review";
		}*/