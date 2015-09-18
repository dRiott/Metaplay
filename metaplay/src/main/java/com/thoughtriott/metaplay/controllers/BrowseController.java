package com.thoughtriott.metaplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtriott.metaplay.data.services.AlbumService;
import com.thoughtriott.metaplay.data.services.LocationService;
import com.thoughtriott.metaplay.data.services.PlaylistService;

@Controller
@RequestMapping("/browse")
public class BrowseController {

	
	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	AlbumService albumService;
	
	@RequestMapping(value="/albums", method=RequestMethod.GET)
	public String findAlbums(Model model){
		model.addAttribute("albums", albumService.findAllAsList());
		return "browse_albums";
	}
	
	@RequestMapping(value="album/{albumId}")
	public String findAlbum(Model model, @PathVariable("albumId") int albumId) {
		model.addAttribute("album", this.albumService.findAlbumById(albumId));
		return "single_album";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public String find(Model model) {
		return "playlists";
	}
	
	@RequestMapping("/artists")
	public String findArtists(){
		return "browse_artists";
	}
	
	@RequestMapping("/locations")
	public String findLocations(Model model){
		model.addAttribute("locations", locationService.findAllAsList()); 
		return "browse_locations";
	}
	
	@RequestMapping(value="/playlists", method=RequestMethod.GET)
	public String findPlaylists(Model model){
		model.addAttribute("playlists", playlistService.findAllAsList());
		return "browse_playlists";
	}
	
	@RequestMapping(value="playlist/{playlistId}")
	public String findPlaylist(Model model, @PathVariable("playlistId") int playlistId) {
		model.addAttribute("playlist", this.playlistService.findPlaylistById(playlistId));
		return "single_playlist";
	}
	
	@RequestMapping("/recordlabels")
	public String findRecordLabels(){
		return "browse_recordlabels";
	}
	
	@RequestMapping("/tracks")
	public String findGroupMembers(){
		return "browse_tracks";
	}
}
