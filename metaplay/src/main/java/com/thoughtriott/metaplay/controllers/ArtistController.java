package com.thoughtriott.metaplay.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.thoughtriott.metaplay.data.repositories.jpa.AlbumRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.ArtistRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.GenreRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.LocationRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.MemberRepository;
import com.thoughtriott.metaplay.data.wrappers.AmazonService;
import com.thoughtriott.metaplay.data.wrappers.CreateArtistWrapper;
import com.thoughtriott.metaplay.utilities.DateFormatter;

@Controller
@RequestMapping("/artist")
@SessionAttributes("createArtistWrapper")
public class ArtistController extends AmazonService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private DateFormatter dateFormatter;

	
	@RequestMapping("/add")
	public String addArtist() {
		return "artist_add";
	}
	
//	@RequestMapping("/review")
//	public String review(HttpSession session, @Valid @ModelAttribute CreateArtistWrapper createArtistWrapper, Model model, Errors errors) {
//		System.out.println("Invoking review() in ArtistController");
//		if(errors.hasErrors()) {
//			return "artist_add";
//		}
//		//      Save the Artist Image and Album Cover	
//		super.saveImage(createArtistWrapper.getArtistImage(), ARTIST, createArtistWrapper.getName());
//		super.saveImage(createArtistWrapper.getAlbumCover(), ALBUM, createArtistWrapper.getTheNewAlbumName());
//		model.addAttribute("createArtistWrapper", createArtistWrapper);
//		return "artist_review";
//	}
	
	@RequestMapping("/save")
	public String saveArtist(@ModelAttribute CreateArtistWrapper caw, HttpSession session, SessionStatus status) {
		System.out.println("Invoking the saveArtist() from ArtistController.");
		Artist futureArtist = new Artist();
//		CreateArtistWrapper caw = (CreateArtistWrapper) session.getAttribute("createArtistWrapper");
		System.out.println("Artist name about to be set: " + caw.getName());
		futureArtist.setName(caw.getName());
		futureArtist.setBiography(caw.getBiography());
		Artist savedArtist = artistRepository.saveAndFlush(futureArtist);

		
		super.saveImage(caw.getArtistImage(), ARTIST, caw.getName());
		super.saveImage(caw.getAlbumCover(), ALBUM, caw.getTheNewAlbumName());
		
		
		
	// 		Setting/Creating a Location
		System.out.println("Setting/Creating a Location");
		String city = caw.getLocationCity();
		String state = caw.getLocationState();
		System.out.println(city + ", " + state);
		if(locationRepository.findLocationByCityAndState(city, state)!=null) {
			System.out.println("Artist Controller: locationService.findLocation() exists... setting.");
			savedArtist.setLocation(locationRepository.findLocationByCityAndState(city, state));
		} else {
			System.out.println("Artist Controller: locationService.findLocation() doesn't exist: creating & setting.");
			savedArtist.setLocation(locationRepository.saveAndFlush(new Location(city, state)));
		}
		
		
		
	//		Setting/Creating a Genre
		System.out.println("Setting/Creating a Genre");
		String genreName = caw.getGenreName();
		caw.getNewGenreDescription();
		if(!genreName.equals("** New Genre **") && genreRepository.findGenreByName(genreName).size()!=0) {
			savedArtist.setGenre(genreRepository.findGenreByName(genreName).get(0));
		} else if(genreName.equals("** New Genre **")) {
			savedArtist.setGenre(genreRepository.saveAndFlush(new Genre(caw.getNewGenreName(),caw.getNewGenreDescription())));
		}
		

	// 		adding members to the artist
		System.out.println("Going to add members to the artist");
		String[][] members = new String[][]{
				{caw.getMember1(), caw.getMember1StageName()},
				{caw.getMember2(), caw.getMember2StageName()},
				{caw.getMember3(), caw.getMember3StageName()},
				{caw.getMember4(), caw.getMember4StageName()},
				{caw.getMember5(), caw.getMember5StageName()},
				{caw.getMember6(), caw.getMember6StageName()}
		};
				
		for (int i = 0; i<members.length; i++) {
			if (!members[i][0].equals("")) {
				System.out.println("members[i][0] wasn't .equals(\"\"), (Full Name) : " + members[i][0]);
				String[] nameArray = memberRepository.splitFullName(members[i][0]);
				
				String lastName = nameArray[nameArray.length-1];
				System.out.println("The returned member's last name: " + lastName);
				if(memberRepository.findMemberByLastName(lastName)!=null && memberRepository.findMemberByLastName(lastName).size()==0) {
					Member newMember = memberRepository.setNameFromArray(nameArray);
					//add stage name if it exists
					if(!members[i][1].equals("")){
						System.out.println("members[i][1] (The Stage Name) :" + members[i][1]);
						newMember.setStageName(members[i][1]);
						savedArtist.addMember(memberRepository.saveAndFlush(newMember));				
					} else {
						savedArtist.addMember(memberRepository.saveAndFlush(newMember));				
					}
				} else {
					savedArtist.addMember(memberRepository.findMemberByLastName(lastName).get(0));
				}
			}
		}
		
	//		Setting/Creating an Album
		System.out.println("Setting/Creating an Album");
		if (caw.getAlbumNameFromList().equals("** Do Not Add Album Now **") || caw.getAlbumNameFromList().contains("exist")) {
			// do nothing, they don't want to add an album.
			System.out.println("ArtistController: ** Do Not Add Album Now ** or No Albums exist, add one!");
		}  else if (caw.getAlbumNameFromList().equals("** New Album **")) {
			// they're adding a new album!
			System.out.println("ArtistController: ** New Album ** is about to be created...");
			Album newAlbum = new Album();
				newAlbum.setName(caw.getTheNewAlbumName());
				newAlbum.setNumTracks(Integer.parseInt(caw.getAlbumNumTracks()));
				System.out.println("Date about to be set: " + dateFormatter.getDateFromString(caw.getAlbumReleaseDate()));
				newAlbum.setReleaseDate(dateFormatter.getDateFromString(caw.getAlbumReleaseDate()));
			System.out.println("ArtistController: Album setting successful.");
			System.out.println("About to flush album");
			System.out.println("Album returned : " + albumRepository.saveAndFlush(newAlbum));
			Album returnedAlbum = albumRepository.saveAndFlush(newAlbum);
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
	
	@ModelAttribute(value="genreOptions")
	public List<String> getGenres() {
		return  genreRepository.findAllToFormattedString();
	}
	
	@ModelAttribute(value="albumOptions")
	public List<String> getAlbums() {
		return  albumRepository.findAllToListString();
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
