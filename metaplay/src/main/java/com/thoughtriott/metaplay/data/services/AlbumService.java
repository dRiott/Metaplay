package com.thoughtriott.metaplay.data.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Album;

public class AlbumService {

	@PersistenceContext
	private EntityManager em;
	
	public AlbumService () {
		//no-arg constructor
	}
	
	@Transactional
	public Album createAlbum(String name, String description) {
		em.clear();
		Album a = new Album();
		a.setName(name);
		a.setDescription(description);
		em.persist(a);
		return a;
	}
	
	@Transactional
	public Album createAlbum(String name) {
		em.clear();
		Album a = new Album();
		a.setName(name);
		em.persist(a);
		return a;
	}

	@Transactional
	public Album createAlbum(Album a) {
		em.clear();
		em.persist(a);
		return a;
	}

	
	//grabs all Genres in Genre table
	public Collection<Album> findAllAsCollection() {
		return em.createQuery("SELECT a FROM Album a ORDER BY a.name", Album.class).getResultList();
	}
	
	//grabs the Genre with of certain name
	public Album findAlbumByName(String name) {
		return (Album) em.createQuery("SELECT a FROM Album a WHERE a.name = :name").setParameter("name", name).getSingleResult();

	}
	
	//return a string of all of the cities in that state
	@SuppressWarnings("unchecked")
	public String findAlbumByNameToString(String name) {
		List<Album> album = em.createQuery("SELECT a FROM Album a WHERE a.name = :name").setParameter("name", name).getResultList();
		int size = album.size();
		int indexCounter = 0;
		String albumString = "";
		
		Iterator<Album> it = album.iterator();
		while(it.hasNext()) {
			Album currentAlbum = it.next();
			indexCounter++;
			if (size==1) {
				albumString = "{" + currentAlbum.getName() + "}";
			} else if(indexCounter < size && indexCounter != 1) {
				albumString = albumString + ", " + currentAlbum.getName();
			} else if (indexCounter == size){
				albumString = albumString + ", " + currentAlbum.getName() + "}";
			} else {
				albumString = "{" + currentAlbum.getName();
			}
		}
		return albumString;
	}
	
	//finds Location by Id
	public Album findAlbumById(int id) {
		return (Album) em.createQuery("SELECT a FROM Album a WHERE a.id = :id").setParameter("id", id).getSingleResult();
	}

}