package com.thoughtriott.metaplay.controllers;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtriott.metaplay.data.repositories.AccountRepository;
import com.thoughtriott.metaplay.data.repositories.AlbumRepository;
import com.thoughtriott.metaplay.data.repositories.ArtistRepository;
import com.thoughtriott.metaplay.data.repositories.GenreRepository;
import com.thoughtriott.metaplay.data.repositories.LocationRepository;
import com.thoughtriott.metaplay.data.repositories.PlaylistRepository;
import com.thoughtriott.metaplay.data.repositories.RecordLabelRepository;
import com.thoughtriott.metaplay.data.repositories.TrackRepository;

@Controller
@RequestMapping("/browse")
public class BrowseController {
	
//	@Autowired
//	AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private PlaylistRepository playlistRepository;
	@Autowired
	private RecordLabelRepository recordLabelRepository;
	@Autowired
	private TrackRepository trackRepository;
	
	public BrowseController() {
	}
	
	//for testing purposes.. see metaplay.testControllers.BrowseControllerTest
	public BrowseController(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}

	//Accounts
	@RequestMapping(value="/accounts", method=RequestMethod.GET)
	public String findAccounts(Model model){
		model.addAttribute("accounts", accountRepository.findAll());
		return "browse_accounts";
	}
	
	@RequestMapping(value="account/{accountId}")
	public String findAccount(Model model, @PathVariable("accountId") int accountId) {
		try {
			model.addAttribute("account", accountRepository.getOne(accountId));
			return "single_account";
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
			return "redirect:/browse/accounts";
		}
	}
	
//Albums
	@RequestMapping(value="/albums", method=RequestMethod.GET)
	public String findAlbums(Model model){
		model.addAttribute("albums", albumRepository.findAll());
		return "browse_albums";
	}
	
	@RequestMapping(value="album/{albumId}")
	public String findAlbum(Model model, @PathVariable("albumId") int albumId) {
		model.addAttribute("album", albumRepository.getOne(albumId));
		return "single_album";
	}
	
//Artists
	@RequestMapping(value="/artists", method=RequestMethod.GET)
	public String findArtists(Model model){
		model.addAttribute("artists", artistRepository.findAll());
		return "browse_artists";
	}
	
	@RequestMapping(value="artist/{artistId}")
	public String findArtist(Model model, @PathVariable("artistId") int artistId) {
		model.addAttribute("artist", artistRepository.getOne(artistId));
		return "single_artist";
	}
	
//Genres
	@RequestMapping(value="/genres", method=RequestMethod.GET)
	public String findGenres(Model model){
		model.addAttribute("genres", genreRepository.findAll());
		return "browse_genres";
	}
	
	@RequestMapping(value="genre/{genreId}")
	public String findGenre(Model model, @PathVariable("genreId") int genreId) {
		model.addAttribute("artist", genreRepository.getOne(genreId));
		return "single_genre";
	}
	
//Locations
	@RequestMapping("/locations")
	public String findLocations(Model model){
		model.addAttribute("locations", locationRepository.findAll()); 
		return "browse_locations";
	}
	
	@RequestMapping(value="location/{locationId}")
	public String findLocation(Model model, @PathVariable("locationId") int locationId) {
		model.addAttribute("location", locationRepository.getOne(locationId));
		return "single_location";
	}
	
//Playlists
	@RequestMapping(value="/playlists", method=RequestMethod.GET)
	public String findPlaylists(Model model){
		model.addAttribute("playlists", playlistRepository.findAll());
		return "browse_playlists";
	}
	
	@RequestMapping(value="playlist/{playlistId}")
	public String findPlaylist(Model model, @PathVariable("playlistId") int playlistId) {
		model.addAttribute("playlist", playlistRepository.getOne(playlistId));
		return "single_playlist";
	}
	
//RecordLabels
	@RequestMapping("/recordlabels")
	public String findRecordLabels(Model model){
		model.addAttribute("recordlabels", recordLabelRepository.findAll()); 
		return "browse_recordlabels";
	}
	
	@RequestMapping(value="recordlabel/{recordlabelId}")
	public String findRecordLabel(Model model, @PathVariable("recordlabelId") int recordlabelId) {
		model.addAttribute("recordlabel", recordLabelRepository.getOne(recordlabelId));
		return "single_recordlabel";
	}
	
//Tracks
	@RequestMapping(value="/tracks", method=RequestMethod.GET)
	public String findTracks(Model model){
		model.addAttribute("tracks", trackRepository.findAll());
		return "browse_tracks";
	}
	
	@RequestMapping(value="track/{trackId}")
	public String findTrack(Model model, @PathVariable("trackId") int trackId) {
		model.addAttribute("track", trackRepository.getOne(trackId));
		return "single_track";
	}
}
