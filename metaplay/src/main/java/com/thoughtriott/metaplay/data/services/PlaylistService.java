package com.thoughtriott.metaplay.data.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtriott.metaplay.data.entities.Playlist;

public class PlaylistService {
	
	private List<Playlist> playlists = new LinkedList<>();
	
	public PlaylistService() {
		Playlist drumAndBasePlaylist = this.createPlaylist("Drum and Base Playlist", "These are some dope ass D&B jams" );
		Playlist bluesProject = this.createPlaylist("Blues Playlist", "Everyone gets the Blues.");
		Playlist popHitsProject = this.createPlaylist("Pop Hits Playlist", "Pop it like it's hot.");
		
		//adds each of the above to the List<Playlist> playlists.
		this.playlists.addAll(Arrays.asList(new Playlist[]{drumAndBasePlaylist, bluesProject, popHitsProject}));
	}
	
	public List<Playlist> findAll(){
		return this.playlists;
	}
	
	//searches through List<Playlist> playlists and returns the first one with the same name as the input.
	public Playlist find(String playlistName){
		return this.playlists.stream().filter(p -> {
			return p.getName().equals(playlistName);
		}).collect(Collectors.toList()).get(0);
	}

	//constructs a Playlist with arguments name and description
	private Playlist createPlaylist(String name, String description) {
		Playlist playlist = new Playlist();
		playlist.setName(name);
		playlist.setDescription(description);
		return playlist;
	}
	
}
