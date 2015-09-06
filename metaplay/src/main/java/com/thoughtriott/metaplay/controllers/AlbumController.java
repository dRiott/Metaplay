package com.thoughtriott.metaplay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/album")
public class AlbumController {

	@RequestMapping("/add")
	public String addAlbum(){
		return "404";
	}
	
	@RequestMapping("/find")
	public String findAlbum(){
		return "404";
	}
}
