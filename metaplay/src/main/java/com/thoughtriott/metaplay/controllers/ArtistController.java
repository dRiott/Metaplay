package com.thoughtriott.metaplay.controllers;

import com.thoughtriott.metaplay.data.entities.*;
import com.thoughtriott.metaplay.data.wrappers.AmazonService;
import com.thoughtriott.metaplay.data.wrappers.CreateArtistWrapper;
import org.eclipse.persistence.internal.jpa.rs.metadata.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/artist")
@SessionAttributes("createArtistWrapper")
public class ArtistController extends AmazonService {

	@RequestMapping("/add")
	public String addArtist() {
		return "artist_add";
	}
	
	@RequestMapping("/save")
	public String saveArtist(@ModelAttribute CreateArtistWrapper caw, HttpSession session, SessionStatus status) {
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ BEGIN ARTIST PERSISTENCE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println("ArtistController - saveArtist(): invoking");
		Artist futureArtist = new Artist();
		futureArtist.setName(caw.getName());
		futureArtist.setBiography(caw.getBiography());
		Artist savedArtist = artistRepository.saveAndFlush(futureArtist);

		if(caw.getArtistImage()!=null){
			super.saveImage(caw.getArtistImage(), ARTIST, caw.getName());
		}
		
		if(caw.getAlbumCover()!=null) {
			super.saveImage(caw.getAlbumCover(), ALBUM, caw.getTheNewAlbumName());
		}
		
	
		// ****************** BEGIN LOCATION PERSISTENCE ******************
		System.out.println("Setting/Creating a Location");
		String city = caw.getLocationCity();
		String state = caw.getLocationState();
		String country = caw.getLocationCountry();
		String newCountry = caw.getNewLocationCountry();
		System.out.println(city + ", " + state + ", " + country);
		
		if(country.equals("United States")) {
			if(locationRepository.findLocationByCityAndState(city, state)!=null) {
				System.out.println("Artist Controller: locationService.findLocation() US city/state exists... setting.");
				savedArtist.setLocation(locationRepository.findLocationByCityAndState(city, state));
			} else {
				//united states: new city/state combo
				savedArtist.setLocation(locationRepository.saveAndFlush(new Location(city, state, country)));
			}
		} else {
			//country isn't US, setting state to null
			if(locationRepository.findLocationByCityAndCountry(city, newCountry)!=null) {
				System.out.println("Artist Controller: locationService.findLocation() NON-US city/country exists... setting.");
				savedArtist.setLocation(locationRepository.findLocationByCityAndCountry(city, newCountry));
			} else {
				//nons-US: new city/country combo
				savedArtist.setLocation(locationRepository.saveAndFlush(new Location(city, null, newCountry)));
			}
			
		}
		
		// ****************** BEGIN GENRE PERSISTENCE ******************
		System.out.println("Setting/Creating a Genre");
		String genreName = caw.getGenreName();
		caw.getNewGenreDescription();
		if(!genreName.equals("** New Genre **") && genreRepository.findGenreByName(genreName).size()!=0) {
			savedArtist.setGenre(genreRepository.findGenreByName(genreName).get(0));
		} else if(genreName.equals("** New Genre **")) {
			savedArtist.setGenre(genreRepository.saveAndFlush(new Genre(caw.getNewGenreName(),caw.getNewGenreDescription())));
		}
		
		// ****************** NEW MEMBER PERSISTENCE ******************
		List<Member> realMembers = caw.getMembers();
		Iterator<Member> it = realMembers.iterator();
		while(it.hasNext()) {
			Member currentMember = it.next();
			String[] cMNameArray = memberRepository.splitFullName(currentMember.getUnparsedName());
			
			String lastName = cMNameArray[cMNameArray.length-1];
			System.out.println("The returned member's last name: " + lastName);
			if(memberRepository.findMemberByLastName(lastName)!=null && memberRepository.findMemberByLastName(lastName).size()==0) {
				Member newMember = memberRepository.setNameFromArray(cMNameArray);
				//add stage name if it exists
				if(!currentMember.getStageName().equals("")){
					System.out.println("The Stage Name :" + currentMember.getStageName());
					newMember.setStageName(currentMember.getStageName());
					savedArtist.addMember(memberRepository.saveAndFlush(newMember));				
				} else {
					savedArtist.addMember(memberRepository.saveAndFlush(newMember));				
				}
			} else {
				savedArtist.addMember(memberRepository.findMemberByLastName(lastName).get(0));
			}
		}
		
		// ****************** BEGIN ALBUM PERSISTENCE ******************
		System.out.println("Setting/Creating an Album");
		if (caw.getAlbumNameFromList().equals("** Do Not Add Album Now **") || caw.getAlbumNameFromList().contains("exist")) {
			// do nothing, they don't want to add an album.
			System.out.println("ArtistController: ** Do Not Add Album Now ** or \"No Albums exist, add one!\"");
		}  else if (caw.getAlbumNameFromList().equals("** New Album **")) {
			// they're adding a new album!
			System.out.println("ArtistController: ** New Album ** is about to be created...");
			Album futureAlbum = new Album();
			futureAlbum.setName(caw.getTheNewAlbumName());
			futureAlbum.setNumTracks(Integer.parseInt(caw.getAlbumNumTracks()));
			futureAlbum.setReleaseDate(dateFormatter.getDateFromString(caw.getAlbumReleaseDate()));
			System.out.println("ArtistController: Album setting successful. About to flush album.");
			
			// ****************** BEGIN RECORD LABEL PERSISTENCE ******************
			System.out.println("AlbumController: Setting/Creating a Record Label");
			String recordLabelName = caw.getRecordLabelFromList();
			String recordLabelCity = caw.getRecordLabelCity();
			String recordLabelState = caw.getRecordLabelState();
			if(recordLabelName!="** New Record Label **" && recordLabelRepository.findRecordLabelByName(recordLabelName).size()>0) {
				futureAlbum.setRecordLabel(recordLabelRepository.findRecordLabelByName(recordLabelName).get(0));
			} else if(recordLabelName.equals("** New Record Label **")) {
				String newRecordLabelName = caw.getTheNewRecordLabel();
				RecordLabel rl = recordLabelRepository.saveAndFlush(
					new RecordLabel(newRecordLabelName, locationRepository.findLocationByCityAndState(recordLabelCity, recordLabelState)));
				futureAlbum.setRecordLabel(rl);
			}
			
			Album returnedAlbum = albumRepository.saveAndFlush(futureAlbum);
			System.out.println("Album returned : " + returnedAlbum);
			System.out.println("futureArtist.addAlbum() - About to add the persisted album to the artist.");
			savedArtist.addAlbum(returnedAlbum);
			albumRepository.saveAndFlush(returnedAlbum);
		} else if (caw.getAlbumNameFromList()!="** New Album **" && albumRepository.findAlbumByName(caw.getAlbumNameFromList())!=null) {
			//they've selected from the dropdown... add from the database to the artist.
			System.out.println("ArtistController: They've selected the following artist from the dropdown: " + caw.getAlbumNameFromList());
			Album album = albumRepository.findAlbumByName(caw.getAlbumNameFromList()).get(0);
			savedArtist.addAlbum(album);
			albumRepository.saveAndFlush(album);
		}
		
		System.out.println("About to saveAndFlush savedArtist");
		artistRepository.saveAndFlush(savedArtist);
		status.setComplete();
		
		return "redirect:/artist/add";
	}
	
	// ------------------------------ Model Attributes ------------------------------
	@ModelAttribute("artist")
	public Artist getArtist() {
		return new Artist();
	}
	
	@ModelAttribute("location")
	public Location getLocation() {
		return new Location();
	}
	
	@ModelAttribute("genre")
	public Genre getGenre() {
		return new Genre();
	}
	
	@ModelAttribute("recordLabel")
	public RecordLabel getRecordLabel() {
		return new RecordLabel();
	}
	
	@ModelAttribute("createArtistWrapper")
	public CreateArtistWrapper getCreateArtistWrapper() {
		return new CreateArtistWrapper();
	}
	
	@ModelAttribute(value="genreOptions")
	public List<String> getGenres() {
		return  genreRepository.findAllToFormattedString();
	}
	
	@ModelAttribute(value="albumOptions")
	public List<String> getAlbums() {
		return  albumRepository.findAllToListString();
	}
	
	@ModelAttribute(value="countryOptions")
	public List<String> getCountries() {
		return locationRepository.findAllCountriesToListString();
	}
	
	@ModelAttribute(value="recordLabelOptions")
	public List<String> getRecordLabels() {
		return  recordLabelRepository.findAllAsListString();
	}

	LinkedList<String> statesList = new LinkedList<>(Arrays.asList(new String[] {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado","Connecticut",
            "Delaware", "District Of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
            "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
            "Mississippi", "Missouri", "Montana Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
            "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania","Rhode Island","South Carolina",
            "South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington", "West Virginia", "Wisconsin", "Wyoming"
    }));

	@ModelAttribute("stateOptions")
	public List<String> getStatesArtistLocation () {
		return statesList;
	}	
	
	@ModelAttribute("recordLabelStateOptions")
	public List<String> getStatesRecordLabelLocation () {
		return statesList;
	}
}	

// ------------------------------ Validator ------------------------------
	/*//registering the ArtistValidator with this controller using a WebDataBinder object.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new CreateArtistWrapperValidator());
	}*/
	
	// ------------------------------ Notes / Old Code ------------------------------
	/*	@RequestMapping("/review")
		public String review(HttpSession session, @Valid @ModelAttribute CreateArtistWrapper createArtistWrapper, Model model, Errors errors) {
			System.out.println("Invoking review() in ArtistController");
			if(errors.hasErrors()) {
				return "artist_add";
			}
			//      Save the Artist Image and Album Cover	
			super.saveImage(createArtistWrapper.getArtistImage(), ARTIST, createArtistWrapper.getName());
			super.saveImage(createArtistWrapper.getAlbumCover(), ALBUM, createArtistWrapper.getTheNewAlbumName());
			model.addAttribute("createArtistWrapper", createArtistWrapper);
			return "artist_review";
		}*/

