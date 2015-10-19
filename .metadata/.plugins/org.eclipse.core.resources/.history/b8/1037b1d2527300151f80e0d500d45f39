package com.thoughtriott.metaplay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtriott.metaplay.data.entities.Artist;
import com.thoughtriott.metaplay.data.repositories.jpa.AlbumRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.ArtistRepository;
import com.thoughtriott.metaplay.errors.MetaplayNotFoundException;

@RestController
@RequestMapping("/rest")
public class RestSearchController {

	//private static final String DEFAULT_ARTIST = "Animal Collective";
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	AlbumRepository albumRepository;
	
	@RequestMapping(value="/artist", method=RequestMethod.GET)
	public Artist artists() {
		return artistRepository.findAll().get(0);
	}
	
	@RequestMapping(value="/artist", method=RequestMethod.POST, consumes="application/json")
	public List<Artist> findArtistLike(@RequestParam("query") String query) {
		System.out.println("query is : " + query);
		String dbQuery = query+"%";
		List<Artist> artists = artistRepository.findArtistByNameLike(dbQuery);
		if(artists!=null) {
			System.out.println("Hard-coded album's name: " + artists.get(0).getName());
		} else {
			System.out.println("came back null");
		}
		return artists;
	}

	@RequestMapping(value="/artist/{id}", method=RequestMethod.GET)
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
