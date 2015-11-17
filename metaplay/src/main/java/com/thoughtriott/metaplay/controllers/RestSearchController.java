package com.thoughtriott.metaplay.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.entities.Artist;
import com.thoughtriott.metaplay.data.entities.MetaplayEntity;
import com.thoughtriott.metaplay.data.entities.Track;
import com.thoughtriott.metaplay.data.wrappers.AmazonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rest")
public class RestSearchController extends AmazonService {

	//Repositories were null if initiated statically — this way they get initiated by an AJAX call when search.jsp loads.
	private Map<String, JpaRepository<?, Integer>> repoMap;
	List<MetaplayEntity> allEntitiesList = new ArrayList<>();
	
	//populate allEntitiesList with EVERY entity.. what a bad idea!
	@RequestMapping(value="/loadAllEntities", method=RequestMethod.GET, consumes="application/json")
	@ResponseBody
	public String loadAllEntities() {
		repoMap = new HashMap<>();
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.AccountRepository", accountRepository);
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.AlbumRepository", albumRepository);
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.ArtistRepository", artistRepository);
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.GenreRepository", genreRepository);
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.LocationRepository", locationRepository);
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.MemberRepository", memberRepository);
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.PlaylistRepository", playlistRepository);
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.RecordLabelRepository", recordLabelRepository);
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.RoleRepository", roleRepository);
	 	repoMap.put("com.thoughtriott.metaplay.data.repositories.jpa.TrackRepository", trackRepository);

	 	for (String key : repoMap.keySet()) {
			JpaRepository<?, Integer> repository = repoMap.get(key);

			@SuppressWarnings("unchecked")
			List<MetaplayEntity> entityList = (List<MetaplayEntity>) repository.findAll();
			
			Iterator<MetaplayEntity> it = entityList.iterator();
			while(it.hasNext()) {
				allEntitiesList.add(it.next());
			}
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString("Success");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//**************************** BEGIN Request Handling Methods **************************** \\
	//gets the account id for the profile link in fragment: header.jsp (shows on every page if user is logged in)
	@RequestMapping(value="/account", method=RequestMethod.POST, consumes="application/json")
	public Account findAccount(@RequestParam("query") String query) {
		return accountRepository.findAccountByAccountname(query).get(0);
	}
	
	//for search for artists input box
	@RequestMapping(value="/artist", method=RequestMethod.POST, consumes="application/json")
	public List<Artist> findArtistLike(@RequestParam("query") String query) {
		return artistRepository.findArtistByNameLike(query+"%");
	}
	
	//for search for track ID by track name on Upload_Mp3 page
	@RequestMapping(value="/track", method=RequestMethod.POST, consumes="application/json")
	public List<Track> findTrackId(@RequestParam("query") String query) {
		return trackRepository.findTrackByNameLike("%"+query+"%");
	}
	
	//random result button
	@RequestMapping(value="/singlerandom", method=RequestMethod.GET, consumes="application/json")
	public Object findSingleRandom() {
		Collections.shuffle(allEntitiesList);
		if(allEntitiesList.size()>0){
			return allEntitiesList.get(0);
		} else {
			return null;
		}
	}

	//for searching every single entity with a PROVIDED QUERY - AJAX POST METHOD.
	@RequestMapping(value="/allentities", method=RequestMethod.POST, consumes="application/json")
	public List<Object> findFromAllEntitiesLike(@RequestParam("query") String query) {
		List<Object> matchingEntities = new ArrayList<>();
		for(MetaplayEntity me : allEntitiesList) {
			if (me.name.contains(query)) {
				matchingEntities.add(me);
			}
		}
		return matchingEntities;
	}	
	
	//random result button
	@RequestMapping(value="/checkForAudio", method=RequestMethod.GET, consumes="application/json")
	public boolean getAudio(@RequestParam("id") String id, @RequestParam("name") String filename) {
		System.out.println("the Id: " + id + ", and the name: " + filename);
		return checkForAudio(id, filename);
	}
	
}

// ------------------------------ Notes / Old Code ------------------------------
	/*@RequestMapping(value="/artist/{id}", method=RequestMethod.GET)
		public @ResponseBody Artist artistById(@PathVariable Integer id) {
			Artist artist = artistRepository.getOne(id);
			if(artist == null) {
				throw new MetaplayNotFoundException(id);
			}
			return artist;
	}*/
	
	//working with ResponseEntity to carry metadata in addition to the object
	/*@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<Artist> artistByIdWithMetadata(@PathVariable Integer id) {
			Artist artist = artistRepository.getOne(id);
			HttpStatus status = artist != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
			return new ResponseEntity<Artist> (artist, status);
	}*/
