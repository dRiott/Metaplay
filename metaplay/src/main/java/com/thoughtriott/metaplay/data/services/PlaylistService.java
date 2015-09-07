package com.thoughtriott.metaplay.data.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtriott.metaplay.data.entities.Playlist;

public class PlaylistService {

	private List<Playlist> playlists = new LinkedList<>();

	public PlaylistService() {
		Playlist drumAndBassPlaylist = this.createPlaylist("Drum and Bass Playlist",
				"These are some dope ass D&B jams! HARDCODED IN PLAYLISTSERVICE CONSTRUCTOR", 1);
		Playlist bluesProject = this.createPlaylist("Blues Playlist", "Everyone gets the Blues. HARDCODED IN PLAYLISTSERVICE CONSTRUCTOR", 2);
		Playlist popHitsProject = this.createPlaylist("Pop Hits Playlist", "Pop it like it's hot. HARDCODED IN PLAYLISTSERVICE CONSTRUCTOR", 3);

		// adds each of the above to the List<Playlist> playlists.
		this.playlists.addAll(Arrays.asList(new Playlist[] { drumAndBassPlaylist, bluesProject, popHitsProject }));
	}

	public List<Playlist> findAll() {
		return this.playlists;
	}

	// searches through List<Playlist> playlists and returns the first one with
	// the same name as the input.
//	public Playlist find(int id) {
//
//		for (Playlist p : playlists) {
//			if (p.getId() == id) {
//				return p;
//			}
//		}
//		Playlist fakePlaylist = new Playlist();
//		 fakePlaylist.setDescription("There were no playlists found.");
//		return fakePlaylist;
//	}
	
	public Playlist find(int playlistId){
		return this.playlists.stream().filter(p -> {
			return p.getId()==playlistId;
		}).collect(Collectors.toList()).get(0);
	}

	// constructs a Playlist with arguments name and description
	private Playlist createPlaylist(String name, String description, int playlistId) {
		Playlist playlist = new Playlist();
		playlist.setName(name);
		playlist.setDescription(description);
		playlist.setId(playlistId);
		return playlist;
	}

}
