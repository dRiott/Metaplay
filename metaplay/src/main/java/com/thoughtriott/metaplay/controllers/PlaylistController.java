package com.thoughtriott.metaplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Playlist;
import com.thoughtriott.metaplay.data.repositories.PlaylistRepository;

@Controller
@RequestMapping("/playlist")
@SessionAttributes("playlist")
public class PlaylistController {

	@Autowired
	private PlaylistRepository playlistRepository;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addPlaylist(){
		return "playlist_add";
	}
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Playlist playlist) {
		System.out.println("Invoking review() in PlaylistController");
		return "playlist_review";
	}

	@RequestMapping(value="/save")
	public String savePlaylist(@ModelAttribute Playlist playlist, SessionStatus status){
		System.out.println("invoking savePlaylist");
		System.out.println(playlist); 
		return "redirect:/playlist/add";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public String find(Model model) {
		model.addAttribute("playlists", playlistRepository.findAll());
		return "playlists";
	}
	
	@RequestMapping(value="/{playlistId}")
	public String findPlaylist(Model model, @PathVariable("playlistId") int playlistId) {
		model.addAttribute("playlist", playlistRepository.getOne(playlistId));
		return "single_playlist";
	}

	
	//adds a new Playlist() to the model
	@ModelAttribute("playlist")
	public Playlist getPlaylist() {
		return new Playlist();
	}


}
