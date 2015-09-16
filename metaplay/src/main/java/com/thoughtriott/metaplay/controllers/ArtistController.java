package com.thoughtriott.metaplay.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

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
import com.thoughtriott.metaplay.data.services.RecordLabelService;
import com.thoughtriott.metaplay.data.wrappers.CreateArtistWrapper;
import com.thoughtriott.metaplay.utilities.DateFormatter;

@Controller
@RequestMapping("/artist")
@SessionAttributes("artist")
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
	private RecordLabelService recordLabelService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private DateFormatter dateFormatter;

	
	@RequestMapping("/add")
	public String addArtist() {
		System.out.println("Adding a new Artist to the model with the @ModelAttribute annotation.");
		return "artist_add";
	}
	
	@RequestMapping("/review")
	public String review(HttpSession session, @ModelAttribute CreateArtistWrapper createArtistWrapper) {
		System.out.println("Invoking review() in ArtistController");
		session.setAttribute("createArtistWrapper", createArtistWrapper);
		return "artist_review";
	}
	
	@RequestMapping("/save")
	public String saveArtist(/*@Valid*/ @ModelAttribute CreateArtistWrapper createArtistWrapper, Errors errors, SessionStatus status, HttpSession session) {
		System.out.println("Invoking the saveArtist() from ArtistController.");
		
		//validation. The errors parameter must be directly after the ModelAttribute with the @Valid annotation.
		if(!errors.hasErrors()) {
			System.out.println("The artist name field was validated.");
		} else{
			System.out.println("The artist did not validate.");
		}
		
		Artist futureArtist = new Artist();
		CreateArtistWrapper caw = (CreateArtistWrapper) session.getAttribute("createArtistWrapper");
		String artistName = caw.getName();
		futureArtist.setName(artistName);
		String artistBiography = caw.getBiography();
		futureArtist.setBiography(artistBiography);
		
	// 		Setting/Creating a Location
		System.out.println("Setting/Creating a Location");
		String city = caw.getLocationCity();
		String state = caw.getLocationState();
		if(locationService.findLocation(city, state)!=null) {
			Location l = locationService.findLocation(city, state);
			futureArtist.setLocation(l);
		} else {
			Location l = locationService.createLocation(city, state);
			futureArtist.setLocation(l);
		}
		
	// 		Setting/Creating a Record Label
		System.out.println("Setting/Creating a Record Label");
		String recordLabelName = caw.getRecordLabelName();
		if(recordLabelService.findRecordLabelByName(recordLabelName)!=null) {
			RecordLabel rl = (RecordLabel) recordLabelService.findRecordLabelByName(recordLabelName);
			futureArtist.setRecordLabel(rl);
		} else {
			RecordLabel rl = (RecordLabel) recordLabelService.createRecordLabel(recordLabelName, locationService.findLocation(city, state));
			futureArtist.setRecordLabel(rl);
		}
		
	//		Setting/Creating a Genre
		System.out.println("Setting/Creating a Genre");
		String genreName = caw.getGenreName();
		if(genreService.findGenreByName(genreName)!=null) {
			Genre g = (Genre) genreService.findGenreByName(genreName);
			futureArtist.setGenre(g);
		} else {
			Genre g = (Genre) genreService.createGenre(genreName);
			futureArtist.setGenre(g);
		}

	// 		adding members to the artist
		System.out.println("Going to add members to the artist");
		String member1fullName = caw.getMember1();
		System.out.println(member1fullName + " whodunit...?");
		if (member1fullName!=null) {
			String[] nameArray = memberService.splitFullName(member1fullName);
			Member newMember = (Member) memberService.createFromNameArray(nameArray);
			System.out.println(newMember);
			futureArtist.addMember(newMember);
		}

		String member2fullName = caw.getMember2();
		System.out.println(member2fullName + "blah blah");
		if (member2fullName!=null) {
			String[] nameArray = memberService.splitFullName(member2fullName);
			Member newMember2 = (Member) memberService.createFromNameArray(nameArray);
			System.out.println(newMember2);
			futureArtist.addMember(newMember2);
		}
		
		String member3fullName = caw.getMember3();
		System.out.println("Member 3: " + member3fullName);
		if (member3fullName!=null) {
			String[] nameArray = memberService.splitFullName(member3fullName);
			Member newMember3 = (Member) memberService.createFromNameArray(nameArray);
			System.out.println(newMember3);
			futureArtist.addMember(newMember3);
		}
		
		String member4fullName = caw.getMember4();
		System.out.println("Member 4: " + member4fullName);
		if (member4fullName!=null) {
			String[] nameArray = memberService.splitFullName(member4fullName);
			Member newMember4 = (Member) memberService.createFromNameArray(nameArray);
			System.out.println(newMember4);
			futureArtist.addMember(newMember4);
		}
		
	//		Setting/Creating an Album
		System.out.println("Setting/Creating an Album");
		String albumName = caw.getAlbumName();
		if(albumService.findAlbumByName(albumName)!=null) {
			Album a = (Album) albumService.findAlbumByName(albumName);
			futureArtist.addAlbum(a);
		} else {
			int albumNumTracks = Integer.parseInt(caw.getAlbumNumTracks());
			Date albumReleaseDate = dateFormatter.getDateFromString(caw.getAlbumReleaseDate()); 
	//			String albumAlbumCover = caw.getAlbumAlbumCover(); //no corresponding field in Album exists yet...
	//			a.setAlbumCover(albumAlbumCover);

			Album a = new Album();
			a.setName(albumName);
			a.setNumTracks(albumNumTracks);
			a.setReleaseDate(albumReleaseDate);
			futureArtist.addAlbum(a);
		}
		System.out.println("artistService.createArtist about to be called...");
		artistService.createArtist(futureArtist);
		System.out.println("artistService.createArtist called.");

		//setComplete wipes the session of the info that we passed to the review page
		//so that when we redirect to the /artist/add page, a blank form is displayed.
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
		return new LinkedList<>(Arrays.asList(new String[] { "Seattle", "Los Angeles", "Denver",
				"San Francisco", "Chicago", "Atlanta", "Dallas", "Portland", "Other" }));
	}
	
	@ModelAttribute(value="genreOptions")
	public List<String> getGenres() {
		return  new LinkedList<>(Arrays.asList(new String[] { "Blues", "Rock", "Juke",
				"D&B", "Jazz Hop", "Hip Hop", "BasedGod", "Drugs", "Other" }));
	}
}
