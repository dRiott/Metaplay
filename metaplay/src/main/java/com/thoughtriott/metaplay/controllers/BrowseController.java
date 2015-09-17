package com.thoughtriott.metaplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtriott.metaplay.data.services.PlaylistService;

@Controller
@RequestMapping("/browse")
public class BrowseController {

	
	@Autowired
	PlaylistService playlistService;
	
	@RequestMapping(value="/albums", method=RequestMethod.GET)
	public String findAlbums(Model model){
		return "browse_albums";
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
	public String findLocations(){
		return "browse_locations";
	}
	
	@RequestMapping(value="/playlists", method=RequestMethod.GET)
	public String findPlaylists(Model model){
		model.addAttribute("playlists", playlistService.findAll("p1", "p2", "p3"));
		return "browse_playlists";
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
