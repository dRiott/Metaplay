package com.thoughtriott.metaplay.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
import com.thoughtriott.metaplay.data.entities.Genre;
import com.thoughtriott.metaplay.data.entities.Location;
import com.thoughtriott.metaplay.data.entities.Member;
import com.thoughtriott.metaplay.data.entities.RecordLabel;
import com.thoughtriott.metaplay.data.services.AlbumService;
import com.thoughtriott.metaplay.data.services.ArtistService;
import com.thoughtriott.metaplay.data.services.GenreService;
import com.thoughtriott.metaplay.data.services.LocationService;
import com.thoughtriott.metaplay.data.services.MemberService;
import com.thoughtriott.metaplay.data.wrappers.CreateArtistWrapper;
import com.thoughtriott.metaplay.utilities.DateFormatter;

@Controller
@RequestMapping("/artist")
@SessionAttributes("createArtistWrapper")
public class ArtistController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ArtistService artistService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private DateFormatter dateFormatter;

	
	@RequestMapping("/add")
	public String addArtist() {
		return "artist_add";
	}
	
	@RequestMapping("/review")
	public String review(HttpSession session, @Valid @ModelAttribute CreateArtistWrapper createArtistWrapper, Errors errors) {
		System.out.println("Invoking review() in ArtistController");
		if(errors.hasErrors()) {
			return "artist_add";
		}
		session.setAttribute("createArtistWrapper", createArtistWrapper);
		return "artist_review";
	}
	
	@RequestMapping("/save")
	public String saveArtist(HttpSession session, SessionStatus status) {
		System.out.println("Invoking the saveArtist() from ArtistController.");
		Artist futureArtist = new Artist();
		CreateArtistWrapper caw = (CreateArtistWrapper) session.getAttribute("createArtistWrapper");
		System.out.println("Artist name about to be set: " + caw.getName());
		futureArtist.setName(caw.getName());
		futureArtist.setBiography(caw.getBiography());
		
	// 		Setting/Creating a Location
		System.out.println("Setting/Creating a Location");
		String city = caw.getLocationCity();
		String state = caw.getLocationState();
		if(locationService.findLocation(city, state)!=null) {
			System.out.println("Artist Controller: locationService.findLocation() exists... setting.");
			futureArtist.setLocation(locationService.findLocation(city, state));
		} else {
			System.out.println("Artist Controller: locationService.findLocation() doesn't exist: creating & setting.");
			futureArtist.setLocation(locationService.createLocation(city, state));
		}
		
		
	//		Setting/Creating a Genre
		System.out.println("Setting/Creating a Genre");
		String genreName = caw.getGenreName();
		caw.getNewGenreDescription();
		if(genreName!="** New Genre **" && genreService.findGenreByName(genreName)!=null) {
			futureArtist.setGenre(genreService.findGenreByName(genreName));
		} else if(genreName.equals("** New Genre **")) {
			futureArtist.setGenre(genreService.createGenre(caw.getNewGenreName(),caw.getNewGenreDescription()));
		}
		

	// 		adding members to the artist
		System.out.println("Going to add members to the artist");
		ArrayList<String> members = new ArrayList<String>();
		members.add(caw.getMember1());
		members.add(caw.getMember2());
		members.add(caw.getMember3());
		members.add(caw.getMember4());
		members.add(caw.getMember5());
		members.add(caw.getMember6());
		
		for (String memberFullName : members) {
			if (!memberFullName.isEmpty()) {
				String[] nameArray = memberService.splitFullName(memberFullName);
				Member newMember = (Member) memberService.setNameFromArray(nameArray);
				newMember.setStageName(caw.getMember1StageName());
				System.out.println(newMember);
				if(memberService.findMemberByName(newMember.getLastName())==null) {
					futureArtist.addMember(memberService.createMember(newMember));				
				} else {
					futureArtist.addMember(memberService.findMemberByName(newMember.getLastName()));
				}		
			}
		}
		
	//		Setting/Creating an Album
		System.out.println("Setting/Creating an Album");
		
		if (caw.getAlbumNameFromList().equals("** Do Not Add Album Now **") || caw.getAlbumNameFromList().contains("exist")) {
			// do nothing, they don't want to add an album.
			System.out.println("** Do Not Add Album Now ** or No Albums exist, add one!");
		}  else if (caw.getAlbumNameFromList().equals("** New Album **")) {
			// they're adding a new album!
			System.out.println("** New Album ** is about to be created...");
			Album a = new Album();
				a.setName(caw.getTheNewAlbumName());
				a.setNumTracks(Integer.parseInt(caw.getAlbumNumTracks()));
				a.setReleaseDate(dateFormatter.getDateFromString(caw.getAlbumReleaseDate()));
			futureArtist.addAlbum(albumService.createAlbum(a));
		} else if (caw.getAlbumNameFromList()!="** New Album **" && albumService.findAlbumByName(caw.getAlbumNameFromList())!=null) {
			//they've selected from the dropdown... add from the database to the artist.
			System.out.println("They've selected the following artist from the dropdown: " + caw.getAlbumNameFromList());
			futureArtist.addAlbum(albumService.findAlbumByName(caw.getAlbumNameFromList()));
		}
		
		artistService.createArtist(futureArtist);

		status.setComplete();
		return "redirect:/artist/add";
	}
	
	@RequestMapping("/find")
	public String findArtist() {
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
	
	@ModelAttribute(value="locationOptions")
	public List<String> getLocations() {
		return locationService.findDistinctStatesToString();
	}
	
	@ModelAttribute(value="genreOptions")
	public List<String> getGenres() {
		return  genreService.findAllAsListString();
	}

	
	@ModelAttribute(value="albumOptions")
	public List<String> getAlbums() {
		return  albumService.findAllAsListString();
	}
	
	@ModelAttribute("stateOptions")
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
}
