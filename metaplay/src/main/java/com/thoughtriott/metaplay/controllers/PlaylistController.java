package com.thoughtriott.metaplay.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
	public String addPlaylist(Model model, HttpSession session){
//		session.setAttribute("token", "12345");
		System.out.println("Adding a new Playlist to the model with the @ModelAttribute annotation.");
		System.out.println("invoking addPlaylist");
		return "playlist_add";
	}
	
	//adds a new Playlist() to the model
	@ModelAttribute("playlist")
	public Playlist getPlaylist() {
		return new Playlist();
	}

	//adds "typeOptions" List to the model
	@ModelAttribute("typeOptions")
	public List<String> getTypes () {
		return new LinkedList<>(Arrays.asList(new String[] { "Solo", "Shared"}));
	}
	
	@RequestMapping(value="/save")
	public String savePlaylist(@ModelAttribute Playlist playlist){
		System.out.println("invoking savePlaylist");
		System.out.println(playlist); //invoking toString Method
		return "added";
	}
	
}
