package com.thoughtriott.metaplay.data.repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.Artist;

public class ArtistRepositoryImpl implements ArtistRepositoryCustom {


	@Autowired
	private ArtistRepository artistRepository;
	
	//returns List<String> of each Artist
	@Override
	public List<String> findAllToListString() {
		List<Artist> artistList = artistRepository.findAll();
		Iterator<Artist> it = artistList.iterator();
		List<String> artistStrList = new ArrayList<String>();
		while(it.hasNext()) {
			Artist a = it.next();
			artistStrList.add(a.getName());
		}
		if(artistList.size()==0) {
			System.out.println("The results list was empty.");
			artistStrList.add("No Artists exist, add one!");
			artistStrList.add("** New Artist **");
			return artistStrList;
		} else {
			artistStrList.add("** New Artist **");
			return artistStrList;
		}
	}
	
	
	//return a string of all of the Artists by given name
	public String findAristByNameToFormattedString(String name) {
		List<Artist> artists = artistRepository.findAll();
		int indexCounter = 0;
		String artistString = "";
		
		Iterator<Artist> it = artists.iterator();
		while(it.hasNext()) {
			Artist currentArtist = it.next();
			indexCounter++;
			if (artists.size()==1) {
				artistString = "{" + currentArtist.getName() + "}";
			} else if(indexCounter < artists.size() && indexCounter != 1) {
				artistString = artistString + ", " + currentArtist.getName();
			} else if (indexCounter == artists.size()){
				artistString = artistString + ", " + currentArtist.getName() + "}";
			} else {
				artistString = "{" + currentArtist.getName();
			}
		}
		return artistString;
	}
	
}
