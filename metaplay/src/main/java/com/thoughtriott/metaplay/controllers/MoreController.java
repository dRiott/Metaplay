package com.thoughtriott.metaplay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/more")
public class MoreController {

	@RequestMapping("/recordlabels")
	public String findRecordLabels(){
		return "404";
	}
	
	@RequestMapping("/groupmembers")
	public String findGroupMembers(){
		return "404";
	}
	
	@RequestMapping("/locations")
	public String findLocations(){
		return "404";
	}
}
