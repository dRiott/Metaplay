package com.thoughtriott.metaplay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtriott.metaplay.data.entities.Artist;
import com.thoughtriott.metaplay.data.repositories.jpa.ArtistRepository;
import com.thoughtriott.metaplay.errors.MetaplayNotFoundException;

@RestController
@RequestMapping("/search")
public class RestSearchController {

	private static final String DEFAULT_ARTIST = "Animal Collective";

	@Autowired
	ArtistRepository artistRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public Artist artists(@RequestParam(value="artist", defaultValue=DEFAULT_ARTIST) String artist) {
		return artistRepository.findArtistByName(artist);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public Artist saveArtist(@RequestBody Artist artist) {
		return artistRepository.saveAndFlush(artist);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Artist artistById(@PathVariable Integer id) {
		Artist artist = artistRepository.getOne(id);
		if(artist == null) {
			throw new MetaplayNotFoundException(id);
		}
		return artist;
	}
	
	//working with ResponseEntity to carry metadata in addition to the object
		/*@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<Artist> artistByIdWithMetadata(@PathVariable Integer id) {
			Artist artist = artistRepository.getOne(id);
			HttpStatus status = artist != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
			return new ResponseEntity<Artist> (artist, status);
		}*/
	
}
