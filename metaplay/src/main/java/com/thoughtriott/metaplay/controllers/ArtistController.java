package com.thoughtriott.metaplay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artist")
public class ArtistController {

	@RequestMapping("/add")
	public String addArtist(Model model){
		return "artist_add";
	}
	
	@RequestMapping("/save")
	public String saveArtist(){
		System.out.println("Invoking the save() method.");
		return "artist_add";
	}
}
