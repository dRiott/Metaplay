package com.thoughtriott.metaplay.controllers;

import javax.servlet.http.HttpSession;

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
import com.thoughtriott.metaplay.data.services.PlaylistService;

@Controller
@RequestMapping("/playlist")
@SessionAttributes("playlist")
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;
	
	//demonstrates adding an Attribute to the HttpSession (pulled out in method below)
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addPlaylist(Model model, HttpSession session){
//		session.setAttribute("token", "12345");
		System.out.println("Adding a new Playlist to the model with the @ModelAttribute annotation.");
		System.out.println("invoking addPlaylist");
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
		System.out.println(playlist); //invoking toString Method
		//setComplete wipes the session of the info that we passed to the review page
				//so that when we redirect to the /artist/add page, a blank form is displayed.
		status.setComplete();
		return "redirect:/playlist/add";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public String find(Model model) {
		model.addAttribute("playlists", this.playlistService.findAll("p1", "p2", "p3"));
		return "playlists";
	}
	
	@RequestMapping(value="/{playlistId}")
	public String findPlaylist(Model model, @PathVariable("playlistId") int playlistId) {
		model.addAttribute("playlist", this.playlistService.findPlaylistById(playlistId));
		return "single_playlist";
	}

	
	//adds a new Playlist() to the model
	@ModelAttribute("playlist")
	public Playlist getPlaylist() {
		return new Playlist();
	}


}
