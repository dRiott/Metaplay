package com.thoughtriott.metaplay.data.repositories.jpa;

import com.thoughtriott.metaplay.data.entities.Album;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AlbumRepositoryImpl implements AlbumRepositoryCustom {

	@Autowired
	AlbumRepository albumRepository;
	
	//returns List<String> of each Album
	@Override
	public List<String> findAllToListString() {
		List<Album> albumList = albumRepository.findAll();
		Iterator<Album> it = albumList.iterator();
		List<String> albumStringList = new LinkedList<>();
		while(it.hasNext()) {
			Album a = it.next();
			albumStringList.add(a.getName());
		}
		if(albumList.size()==0) {
			System.out.println("AlbumRepositoryImpl: findAllAsListString() - The List<Album> albumList was empty.");
			albumStringList.add("No Albums exist, add one!");
			albumStringList.add("** New Album **");
			albumStringList.add("** Do Not Add Album Now **");
			return albumStringList;
		} else {
			albumStringList.add("** New Album **");
			albumStringList.add("** Do Not Add Album Now **");
			return albumStringList;
		}
	}

	//return a single, formatted String of all of the albums of a given name
	@Override
	public String findAlbumByNameToFormattedString(String name) {
		List<Album> albums = albumRepository.findAll();
		int indexCounter = 0;
		String albumString = "";
		
		Iterator<Album> it = albums.iterator();
		while(it.hasNext()) {
			Album currentAlbum = it.next();
			indexCounter++;
			if (albums.size()==1) {
				albumString = "{" + currentAlbum.getName() + "}";
			} else if(indexCounter < albums.size() && indexCounter != 1) {
				albumString = albumString + ", " + currentAlbum.getName();
			} else if (indexCounter == albums.size()){
				albumString = albumString + ", " + currentAlbum.getName() + "}";
			} else {
				albumString = "{" + currentAlbum.getName();
			}
		}
		return albumString;
	}
		

}
