package com.thoughtriott.metaplay.data.repositories;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Playlist;

@Repository
public class PlaylistService {

	@PersistenceContext
	private EntityManager em;
	
	private List<Playlist> playlists = new LinkedList<>();

	public PlaylistService() {
		//no arg constructor
	}
	
	public PlaylistService(String p1, String p2, String p3) {
	}
	
// ------------------------------- Creates ---------------------------------------	
	
	// constructs a Playlist with arguments name and description
		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public Playlist createPlaylist(String name, String description) {
			em.clear();
			Playlist p = new Playlist();
			p.setName(name);
			p.setDescription(description);
			em.persist(p);
			em.close();
			return p;
		}

// ------------------------------- TechBus Spring MVC Tutorial Stuff ---------------------------------------
		@Transactional
		public List<Playlist> findAll(String p1, String p2, String p3) {
		Playlist playlist1 = this.createPlaylist(p1, "These are some dope ass D&B jams!");
		Playlist playlist2 = this.createPlaylist(p2, "Everyone gets the Blues.");
		Playlist playlist3 = this.createPlaylist(p3, "Pop it like it's hot.");
		this.playlists.addAll(Arrays.asList(new Playlist[] { playlist1, playlist2, playlist3 }));
		return this.playlists;
	}
		
// searches through List<Playlist> playlists and returns the first one with
// the same name as the input.
//		public Playlist find(int id) {
//			for (Playlist p : playlists) {
//				if (p.getId() == id) {
//					return p;
//				}
//			}
//			Playlist fakePlaylist = new Playlist();
//			 fakePlaylist.setDescription("There were no playlists found.");
//			return fakePlaylist;
//		}	
		
//------------------------------- Queries ---------------------------------------
	
	//grabs all Locations in Location table
	public List<Playlist> findAllAsList() {
		List<Playlist> playlistList = (List<Playlist>) em.createQuery("SELECT p FROM Playlist p ORDER BY p.name, p.id", Playlist.class).getResultList();
		if(playlistList.size()==0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return playlistList;
		}
	}	
		
		public Playlist findPlaylistById(int id) {		
		@SuppressWarnings("unchecked")
		List<Playlist> playlistList = (List<Playlist>) em.createQuery("SELECT p FROM Playlist p WHERE p.id = :id").setParameter("id", id).getResultList();
		if (playlistList.size() == 0) {
			return null;
		} else if (playlistList.size() > 1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return playlistList.get(0);
		} else {
			return playlistList.get(0);
		}	
	}
		
// ------------------------------- to String ---------------------------------------

		// return a string of all of the playlists with a certain name
		@SuppressWarnings("unchecked")
		public String findPlaylistsByNameToString(String name) {
			List<Playlist> playlists = em.createQuery("SELECT p FROM Playlist p WHERE p.name = :name")
					.setParameter("name", name).getResultList();
			int size = playlists.size();
			int indexCounter = 0;
			String playlistString = "";

			Iterator<Playlist> it = playlists.iterator();
			while (it.hasNext()) {
				Playlist currentPlaylist = it.next();
				indexCounter++;
				if (size == 1) {
					playlistString = "{" + currentPlaylist.getName() + " " + currentPlaylist.getName() + "}";
				} else if (indexCounter < size && indexCounter != 1) {
					playlistString = playlistString + ", " + currentPlaylist.getName() + " " + currentPlaylist.getName();
				} else if (indexCounter == size) {
					playlistString = playlistString + ", " + currentPlaylist.getName() + " " + currentPlaylist.getName()
							+ "}";
				} else {
					playlistString = "{" + currentPlaylist.getName() + " " + currentPlaylist.getName();
				}
			}
			return playlistString;
		}
}
