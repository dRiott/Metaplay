package com.thoughtriott.metaplay.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtriott.metaplay.data.entities.Playlist;
import com.thoughtriott.metaplay.data.services.PlaylistService;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;
	
	@RequestMapping(value="/{playlistId}")
	public String findPlaylist(Model model, @PathVariable("playlistId") int playlistId) {
		model.addAttribute("playlist", this.playlistService.find(playlistId));
		return "playlist";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public String find(Model model) {
		model.addAttribute("playlists", this.playlistService.findAll());
		return "playlists";
	}
	
	//demonstrates adding an Attribute to the HttpSession (pulled out in method below)
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addPlaylist(HttpSession session){
//		session.setAttribute("token", "12345");
		System.out.println("invoking addPlaylist");
		return "playlist_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String savePlaylist(@ModelAttribute Playlist playlist){
		System.out.println("invoking savePlaylist");
		System.out.println(playlist); //invoking toString Method
		return "playlist_add";
	}
	
}
