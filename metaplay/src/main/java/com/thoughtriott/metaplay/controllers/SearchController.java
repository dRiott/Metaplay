package com.thoughtriott.metaplay.controllers;

import com.thoughtriott.metaplay.data.wrappers.AmazonService;
import com.thoughtriott.metaplay.data.wrappers.CreateTrackWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchController extends AmazonService {

	@RequestMapping("/search")
	public String getRestPage() {
		return "search";
	}
	
	// ------------------------------ Model Attributes ------------------------------
	@ModelAttribute("createTrackWrapper")
	public CreateTrackWrapper getCreateTrackWrapper() {
		return new CreateTrackWrapper();
	}
	
	@ModelAttribute(value="artistOptions")
	public List<String> getArtists() {
		return  artistRepository.findAllToListString();
	}
	
	@ModelAttribute(value="albumOptions")
	public List<String> getAlbums() {
		return  albumRepository.findAllToListString();
	}
}
