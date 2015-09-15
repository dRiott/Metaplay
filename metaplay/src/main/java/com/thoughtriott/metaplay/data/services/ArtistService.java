package com.thoughtriott.metaplay.data.services;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Artist;

public class ArtistService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private LocationService locationService;
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private RecordLabelService recordLabelService;
	
	public ArtistService () {
		//no-arg constructor
	}
	
//------------------------------- Creates ---------------------------------------		
	
	@Transactional
	public Artist createArtist(String name, int genreId, int locationId, int recordLabelId, String biography) {
		em.clear();
		Artist a = new Artist();
		a.setName(name);
		a.setBiography(biography);
		a.setLocation(locationService.findLocationById(locationId));
		a.setGenre(genreService.findGenreById(genreId));
		a.setRecordLabel(recordLabelService.findRecordLabelById(recordLabelId));
		em.persist(a);
		return a;
	}
	
	@Transactional
	public Artist createArtist(String name, String biography) {
		em.clear();
		Artist a = new Artist();
		a.setName(name);
		a.setBiography(biography);
		em.persist(a);
		return a;
	}
	
	@Transactional
	public void createArtist(Artist a) {
		em.clear();
		em.persist(a);
	}

//------------------------------- Queries ---------------------------------------	
	
	public List<Artist> findAllArtists() {
		 List<Artist> artistList = (List<Artist>) em.createQuery("SELECT a FROM Artist a ORDER BY a.name", Artist.class).getResultList();
			if(artistList.size()==0) {
				System.out.println("The results list was empty.");
				return null;
			} else {
				return artistList;
			}
	}
	
	public Artist findArtistByName(String name) {		
		@SuppressWarnings("unchecked")
		List<Artist> artistList = (List<Artist>) em.createQuery("SELECT a FROM Artist a WHERE a.name = :name").setParameter("name", name).getResultList();
		if(artistList.size()==0) {
			return null;
		} else if(artistList.size()>1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return artistList.get(0);
		} else {
			return artistList.get(0);
		}
	}
	
	public Artist findArtistById(int id) {		
		@SuppressWarnings("unchecked")
		List<Artist> artistList = (List<Artist>) em.createQuery("SELECT a FROM Artist a WHERE a.id = :id").setParameter("id", id).getResultList();
		if(artistList.size()==0) {
			return null;
		} else if(artistList.size()>1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return artistList.get(0);
		} else {
			return artistList.get(0);
		}
	}
	
	
//------------------------------- to String ---------------------------------------			

	//return a string of all of the Artists by given name
	@SuppressWarnings("unchecked")
	public String findAristByNameToString(String name) {
		List<Artist> artists = em.createQuery("SELECT a FROM Artist a WHERE a.name = :name").setParameter("name", name).getResultList();
		int size = artists.size();
		int indexCounter = 0;
		String artistString = "";
		
		Iterator<Artist> it = artists.iterator();
		while(it.hasNext()) {
			Artist currentArtist = it.next();
			indexCounter++;
			if (size==1) {
				artistString = "{" + currentArtist.getName() + "}";
			} else if(indexCounter < size && indexCounter != 1) {
				artistString = artistString + ", " + currentArtist.getName();
			} else if (indexCounter == size){
				artistString = artistString + ", " + currentArtist.getName() + "}";
			} else {
				artistString = "{" + currentArtist.getName();
			}
		}
		return artistString;
	}

}