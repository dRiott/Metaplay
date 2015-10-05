package com.thoughtriott.metaplay.data.repositories;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.Playlist;

public class PlaylistRepositoryImpl implements PlaylistRepositoryCustom {

	@Autowired
	private PlaylistRepository playlistRepository;
	
	// return a string of all of the playlists with a certain name
	public String findPlaylistByNameToFormattedString(String name) {
		List<Playlist> playlists = playlistRepository.findPlaylistByName(name);
		int indexCounter = 0;
		String playlistString = "";

		Iterator<Playlist> it = playlists.iterator();
		while (it.hasNext()) {
			Playlist currentPlaylist = it.next();
			indexCounter++;
			if (playlists.size() == 1) {
				playlistString = "{" + currentPlaylist.getName() + " " + currentPlaylist.getName() + "}";
			} else if (indexCounter < playlists.size() && indexCounter != 1) {
				playlistString = playlistString + ", " + currentPlaylist.getName() + " " + currentPlaylist.getName();
			} else if (indexCounter == playlists.size()) {
				playlistString = playlistString + ", " + currentPlaylist.getName() + " " + currentPlaylist.getName()
						+ "}";
			} else {
				playlistString = "{" + currentPlaylist.getName() + " " + currentPlaylist.getName();
			}
		}
		return playlistString;
	}

	
	
}
