package com.thoughtriott.metaplay.data.services;

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
	
//------------------------------- Creates ---------------------------------------	
	
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

//------------------------------- Queries ---------------------------------------	
	
	//grabs all Albums in Album table
	public List<Album> findAllAsCollection() {
		List<Album> albumList = (List<Album>) em.createQuery("SELECT a FROM Album a ORDER BY a.name", Album.class).getResultList();
		if(albumList.size()==0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return albumList;
		}
	}
	
	//grabs the Album with of certain name
	public Album findAlbumByName(String name) {
		@SuppressWarnings("unchecked")
		List<Album> albumList = (List<Album>) em.createQuery("SELECT a FROM Album a WHERE a.name = :name").setParameter("name", name).getResultList();
		if(albumList.size()==0) {
			return null;
		} else if(albumList.size()>1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return albumList.get(0);
		} else {
			return albumList.get(0);
		}

	}
	
	//finds Album by Id
	public Album findAlbumById(int id) {		
		@SuppressWarnings("unchecked")
		List<Album> albumList = (List<Album>) em.createQuery("SELECT a FROM Album a WHERE a.id = :id").setParameter("id", id).getResultList();
		if(albumList.size()==0) {
			return null;
		} else if(albumList.size()>1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return albumList.get(0);
		} else {
			return albumList.get(0);
		}
	}
	
//------------------------------- to String ---------------------------------------			
	
	//return a string of all of the albums of a given name
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
	
}