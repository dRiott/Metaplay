package com.thoughtriott.metaplay.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtriott.metaplay.data.entities.Playlist;

@RestController
public class TestRestController {
	
		//method for ajax text call
		@RequestMapping("/AJAX")
		public String getNamesArray(int counter){
			String [] names = {"Bruce Wayne", "Clark Kent", "Peter Parker", "Tony Stark", "Bruce Banner"};
			return names[counter];
		}
		
		//method for ajax json call
		@RequestMapping("/AJAX2")
		public Playlist[] getNameJSON(){
			Playlist p1 = new Playlist("Andrew");
			Playlist p2 = new Playlist("Jamie");
			Playlist p3 = new Playlist("Rob");
			Playlist p4 = new Playlist("Cole");
			Playlist p5 = new Playlist("Bruce");
			
			Playlist [] pArray = {p1, p2, p3, p4, p5};
			
			return pArray;
		}
}
