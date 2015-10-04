package com.thoughtriott.metaplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtriott.metaplay.data.repositories.AccountService;
import com.thoughtriott.metaplay.data.repositories.AlbumService;
import com.thoughtriott.metaplay.data.repositories.ArtistService;
import com.thoughtriott.metaplay.data.repositories.LocationRepository;
import com.thoughtriott.metaplay.data.repositories.PlaylistService;
import com.thoughtriott.metaplay.data.repositories.RecordLabelService;
import com.thoughtriott.metaplay.data.repositories.TrackService;

@Controller
@RequestMapping("/browse")
public class BrowseController {
	
	@Autowired
	AccountService accountService;
	@Autowired
	AlbumService albumService;
	@Autowired
	ArtistService artistService;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	PlaylistService playlistService;
	@Autowired
	RecordLabelService recordLabelService;
	@Autowired
	TrackService trackService;
	
	public BrowseController(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	public BrowseController() {
	}
	
//Accounts
	@RequestMapping(value="/accounts", method=RequestMethod.GET)
	public String findAccounts(Model model){
		model.addAttribute("accounts", accountService.findAllAsList());
		return "browse_accounts";
	}
	@RequestMapping(value="account/{accountId}")
	public String findAccount(Model model, @PathVariable("accountId") int accountId) {
		model.addAttribute("account", accountService.findAccountById(accountId));
		return "single_account";
	}
	
//Albums
	@RequestMapping(value="/albums", method=RequestMethod.GET)
	public String findAlbums(Model model){
		model.addAttribute("albums", albumService.findAllAsList());
		return "browse_albums";
	}
	
	@RequestMapping(value="album/{albumId}")
	public String findAlbum(Model model, @PathVariable("albumId") int albumId) {
		model.addAttribute("album", albumService.findAlbumById(albumId));
		return "single_album";
	}
	
//Artists
	@RequestMapping(value="/artists", method=RequestMethod.GET)
	public String findArtists(Model model){
		model.addAttribute("artists", artistService.findAllAsList());
		return "browse_artists";
	}
	
	@RequestMapping(value="artist/{artistId}")
	public String findArtist(Model model, @PathVariable("artistId") int artistId) {
		model.addAttribute("artist", artistService.findArtistById(artistId));
		return "single_artist";
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
		model.addAttribute("playlists", playlistService.findAllAsList());
		return "browse_playlists";
	}
	
	@RequestMapping(value="playlist/{playlistId}")
	public String findPlaylist(Model model, @PathVariable("playlistId") int playlistId) {
		model.addAttribute("playlist", playlistService.findPlaylistById(playlistId));
		return "single_playlist";
	}
	
//RecordLabels
	@RequestMapping("/recordlabels")
	public String findRecordLabels(Model model){
		model.addAttribute("recordlabels", recordLabelService.findAllAsList()); 
		return "browse_recordlabels";
	}
	
	@RequestMapping(value="recordlabel/{recordlabelId}")
	public String findRecordLabel(Model model, @PathVariable("recordlabelId") int recordlabelId) {
		model.addAttribute("recordlabel", recordLabelService.findRecordLabelById(recordlabelId));
		return "single_recordlabel";
	}
	
//Tracks
	@RequestMapping(value="/tracks", method=RequestMethod.GET)
	public String findTracks(Model model){
		model.addAttribute("tracks", trackService.findAllAsList());
		return "browse_tracks";
	}
	
	@RequestMapping(value="track/{trackId}")
	public String findTrack(Model model, @PathVariable("trackId") int trackId) {
		model.addAttribute("track", trackService.findTrackById(trackId));
		return "single_track";
	}
}
