package com.thoughtriott.metaplay.data.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.thoughtriott.metaplay.data.entities.Playlist;

public class PlaylistService {

	@PersistenceContext
	private EntityManager em;
	
	private List<Playlist> playlists = new LinkedList<>();

	public PlaylistService() {
		//no arg constructor
	}
	
	public PlaylistService(String p1, String p2, String p3) {
	}

	public List<Playlist> findAll(String p1, String p2, String p3) {
		Playlist playlist1 = this.createPlaylist(p1, "These are some dope ass D&B jams!");
		Playlist playlist2 = this.createPlaylist(p2, "Everyone gets the Blues.");
		Playlist playlist3 = this.createPlaylist(p3, "Pop it like it's hot.");
		this.playlists.addAll(Arrays.asList(new Playlist[] { playlist1, playlist2, playlist3 }));
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
	
	
	public Playlist findPlaylistById(int id) {
		return (Playlist) em.createQuery("SELECT p FROM Playlist p WHERE l.id = :id").setParameter("id", id).getSingleResult();
	}

	// constructs a Playlist with arguments name and description
	public Playlist createPlaylist(String name, String description) {
		em.clear();
		em.getTransaction().begin();
		Playlist p = new Playlist();
		p.setName(name);
		p.setDescription(description);
		em.persist(p);
		em.close();
		return p;
	}

}
