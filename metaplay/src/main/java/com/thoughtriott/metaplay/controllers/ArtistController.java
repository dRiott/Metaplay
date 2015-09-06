package com.thoughtriott.metaplay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artist")
public class ArtistController {

	@RequestMapping("/add")
	public String addArtist(){
		return "404";
	}
	
	@RequestMapping("/find")
	public String findArtist(){
		return "404";
	}
}
