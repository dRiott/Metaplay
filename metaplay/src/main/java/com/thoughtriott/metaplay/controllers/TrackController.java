package com.thoughtriott.metaplay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/track")
public class TrackController {

	@RequestMapping("/add")
	public String addTrack(){
		return "404";
	}
	
	@RequestMapping("/find")
	public String findTrack(){
		return "404";
	}
}
