package com.thoughtriott.metaplay.data.repositories.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.Genre;

public class GenreRepositoryImpl implements GenreRepositoryCustom {

	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	public String findGenreByNameToString(String name) {
		List<Genre> genresList = genreRepository.findGenreByName(name);
		int size = genresList.size();
		int indexCounter = 0;
		String genresString = "";
		
		Iterator<Genre> it = genresList.iterator();
		while(it.hasNext()) {
			Genre currentGenre = it.next();
			indexCounter++;
			if (size==1) {
				genresString = "{" + currentGenre.getName() + "}";
			} else if(indexCounter < size && indexCounter != 1) {
				genresString = genresString + ", " + currentGenre.getName();
			} else if (indexCounter == size){
				genresString = genresString + ", " + currentGenre.getName() + "}";
			} else {
				genresString = "{" + currentGenre.getName();
			}
		}
		return genresString;
	}
	
	//returns List<String> of each Genre
	@Override	
	public List<String> findAllToFormattedString() {
		List<Genre> genreList = genreRepository.findAll();
		Iterator<Genre> it = genreList.iterator();
		List<String> genStrList = new ArrayList<String>();
		while (it.hasNext()) {
			Genre genre = it.next();
			genStrList.add(genre.getName());
		}
		if (genreList.size() == 0) {
			System.out.println("The results list was empty.");
			genStrList.add("No Genres exist, add one!");
			genStrList.add("** New Genre **");
			return genStrList;
		} else {
			genStrList.add("** New Genre **");
			return genStrList;
		}
	}
}