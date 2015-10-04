package com.thoughtriott.metaplay.data.repositories;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Artist;

@Repository
public class ArtistService {

	@PersistenceContext
	private EntityManager em;

	public ArtistService () {
		//no-arg constructor
	}
	
//------------------------------- Creates ---------------------------------------		
	

	
	@Transactional
	public Artist createArtist(String name, String biography) {
		em.clear();
		Artist a = new Artist();
		a.setName(name);
		a.setBiography(biography);
		em.persist(a);
		em.close();
		return a;
	}
	
	@Transactional
	public Artist createArtist(String name) {
		em.clear();
		Artist a = new Artist();
		a.setName(name);
		em.merge(a);
		em.close();
		return a;
	}
	
	@Transactional
	public Artist createArtist(Artist a) {
		em.clear();
		em.merge(a);
		em.close();
		return a;
	}

//------------------------------- Queries ---------------------------------------	
	
	public List<Artist> findAllAsList() {
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
	
	public Artist findArtistByNameAndBiography(String name, String biography) {		
		@SuppressWarnings("unchecked")
		List<Artist> artistList = (List<Artist>) em.createQuery("SELECT a FROM Artist a WHERE a.name = :name AND a.biography = :biography").setParameter("name", name).setParameter("biography", biography).getResultList();
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

	//returns List<String> of each Artist
	public List<String> findAllAsListString() {
		@SuppressWarnings("unchecked")
		List<Artist> artistList = (List<Artist>) em.createQuery("SELECT a FROM Artist a ORDER BY a.name").getResultList();
		Iterator<Artist> it = artistList.iterator();
		List<String> artistStrList = new LinkedList<String>();
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