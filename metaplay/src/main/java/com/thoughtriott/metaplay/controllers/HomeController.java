package com.thoughtriott.metaplay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thoughtriott.metaplay.data.entities.Playlist;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String goHome(Model model){
		
		Playlist playlist = new Playlist();
		playlist.setName("First Playlist");
		playlist.setType("Shared Playlist");
		playlist.setDescription("This is the first playlist, hardcoded.");
		
		model.addAttribute("currentPlaylist", playlist);
		
		return "home";
	}
}
