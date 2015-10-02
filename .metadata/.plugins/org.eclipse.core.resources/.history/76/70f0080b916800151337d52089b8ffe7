package com.thoughtriott.metaplay.data.services;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Album;
import com.thoughtriott.metaplay.data.entities.Track;

public class TrackService {

	@PersistenceContext
	private EntityManager em;
	
	public TrackService () {
		//no-arg constructor
	}
	
//------------------------------- Creates ---------------------------------------	
	
	@Transactional
	public Track createTrack(String name, int lengthSeconds, Album album) {
		em.clear();
		Track t = new Track();
		t.setName(name);
		t.setLength(lengthSeconds);
		t.setAlbum(album);
		em.merge(t);
		em.close();
		return t;
	}
	
	@Transactional
	public void createTrack(Track t) {
		String name = t.getName();
		em.clear();
		em.merge(t);
		Track track = findTrack(name);
		System.out.println(track);
		em.close();
	}
	
//------------------------------- Queries ---------------------------------------	
	
	//grabs all Tracks in Track table
	public List<Track> findAllAsList() {
		List<Track> trackList = (List<Track>) em.createQuery("SELECT t FROM Track t ORDER BY t.name", Track.class).getResultList();
		if(trackList.size()==0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return trackList;
		}
	}
	
	// WORKING FROM HERE DOWN!!!!WORKING FROM HERE DOWN!!!!WORKING FROM HERE DOWN!!!!
	
	//grabs all the Tracks with a certain Name
	@SuppressWarnings("unchecked")
	public Track findTrack(String name) {
		List<Track> trackList = (List<Track>) em.createQuery("SELECT t FROM Track t WHERE t.name = :name").setParameter("name", name).getResultList();
		if(trackList.size()==0) {
			return null;
		} else if(trackList.size()>1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return trackList.get(0);
		} else {
			return trackList.get(0);
		}
	}
	
	//finds Track by Id
	public Track findTrackById(int id) {		
		@SuppressWarnings("unchecked")
		List<Track> trackList = (List<Track>) em.createQuery("SELECT t FROM Track t WHERE t.id = :id").setParameter("id", id).getResultList();
		if(trackList.size()==0) {
			return null;
		} else if(trackList.size()>1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return trackList.get(0);
		} else {
			return trackList.get(0);
		}
	}
	
//------------------------------- to String ---------------------------------------				
	
	//returns List<String> of each Track name.
		public List<String> findTracksToString() {
			@SuppressWarnings("unchecked")
			List<String> trackList = (List<String>) em.createQuery("SELECT t.name FROM Track t ORDER BY t.name").getResultList();
			if(trackList.size()==0) {
				System.out.println("The results list was empty.");
				return null;
			} else {
				return trackList;
			}
		}
	
	//grabs all Tracks and formats them into a nicely presented String
	public String findAllAsString() {
		List<String> tracks = findTracksToString();
		Iterator<String> it = tracks.iterator();
		String fullList = "";
		while(it.hasNext()) {
			String track = it.next();
			fullList = fullList + track + "\n";
		}
		return fullList;
	}
}