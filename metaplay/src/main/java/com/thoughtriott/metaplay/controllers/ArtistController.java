package com.thoughtriott.metaplay.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.thoughtriott.metaplay.validators.CreateArtistWrapperValidator;

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
	public String saveArtist(@Valid @ModelAttribute CreateArtistWrapper createArtistWrapper, Errors errors, SessionStatus status, HttpSession session) {
		System.out.println("Invoking the saveArtist() from ArtistController.");
		
		//validation. The errors parameter must be directly after the ModelAttribute with the @Valid annotation.
		if(!errors.hasErrors()) {
			System.out.println("The artist name field was validated.");
		} else{
			System.out.println("The artist did not validate.");
		}
		
		Artist futureArtist = new Artist();
		String artistName = (String) session.getAttribute("name");
		futureArtist.setName(artistName);
		String artistBiography = (String) session.getAttribute("biography");
		futureArtist.setBiography(artistBiography);
		
	// 		Setting/Creating a Location
		String city = (String) session.getAttribute("locationCity");
		String state = (String) session.getAttribute("locationState");
		if(locationService.findLocation(city, state)!=null) {
			Location l = locationService.findLocation(city, state);
			futureArtist.setLocation(l);
		} else {
			Location l = locationService.createLocation(city, state);
			futureArtist.setLocation(l);
		}
		
	// 		Setting/Creating a Record Label
		String recordLabelName = (String) session.getAttribute("recordLabelName");
		if(recordLabelService.findRecordLabelByName(recordLabelName)!=null) {
			RecordLabel rl = (RecordLabel) recordLabelService.findRecordLabelByName(recordLabelName);
			futureArtist.setRecordLabel(rl);
		} else {
			RecordLabel rl = (RecordLabel) recordLabelService.createRecordLabel(recordLabelName, locationService.findLocation(city, state));
			futureArtist.setRecordLabel(rl);
		}
		
	//		Setting/Creating a Genre
		String genreName = (String) session.getAttribute("genreName");
		if(genreService.findGenreByName(genreName)!=null) {
			Genre g = (Genre) genreService.findGenreByName(genreName);
			futureArtist.setGenre(g);
		} else {
			Genre g = (Genre) genreService.createGenre(genreName);
			futureArtist.setGenre(g);
		}

	// 		adding members to the artist
		String member1fullName = (String) session.getAttribute("member1");
		Member member1 = memberService.splitQueryCreate(member1fullName);
		futureArtist.addMember(member1);

		String member2fullName = (String) session.getAttribute("member2");
		Member member2 = memberService.splitQueryCreate(member2fullName);	
		futureArtist.addMember(member2);
		
		String member3fullName = (String) session.getAttribute("member3");
		Member member3 = memberService.splitQueryCreate(member3fullName);
		futureArtist.addMember(member3);
		
		String member4fullName = (String) session.getAttribute("member4");
		Member member4 = memberService.splitQueryCreate(member4fullName);
		futureArtist.addMember(member4);
		
	//		Setting/Creating a Genre
		String albumName = (String) session.getAttribute("albumName");
		if(albumService.findAlbumByName(albumName)!=null) {
			Album a = (Album) albumService.findAlbumByName(albumName);
			futureArtist.addAlbum(a);
		} else {
			int albumNumTracks = Integer.parseInt((String) session.getAttribute("albumNumTracks"));
			Date albumReleaseDate = dateFormatter.getDateFromString((String) session.getAttribute("albumReleaseDate")); 
	//			String albumAlbumCover = (String) session.getAttribute("albumAlbumCover"); //no corresponding field in Album exists yet...
	//			a.setAlbumCover(albumAlbumCover);

			Album a = new Album();
			a.setName(albumName);
			a.setNumTracks(albumNumTracks);
			a.setReleaseDate(albumReleaseDate);
			futureArtist.addAlbum(a);
		}
		
		artistService.createArtist(futureArtist);

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
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new CreateArtistWrapperValidator());
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
