package com.thoughtriott.metaplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtriott.metaplay.data.services.PlaylistService;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public String find(Model model) {
		model.addAttribute("playlists", this.playlistService.findAll());
		return "playlists";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addPlaylist(){
		System.out.println("invoking addPlaylist");
		return "playlist_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String savePlaylist(){
		System.out.println("invoking savePlaylist");
		return "playlist_add";
	}
	
	//Invokes this method if the type param is equal to "shared".
	//Can also just check for presence of a param ("type") with params={"type"}
	@RequestMapping(value="/add", method=RequestMethod.POST, params={"type=shared"})
	public String saveSharedPlaylist(){
		System.out.println("invoking saveSharedPlaylist");
		return "playlist_add";
	}
}
