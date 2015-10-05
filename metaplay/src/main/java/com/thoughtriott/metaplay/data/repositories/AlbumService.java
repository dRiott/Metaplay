//package com.thoughtriott.metaplay.data.repositories;
//
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.thoughtriott.metaplay.data.entities.Album;
//
//@Repository
//public class AlbumService {
//
//	@PersistenceContext
//	private EntityManager em;
//	
//	public AlbumService () {
//		//no-arg constructor
//	}
//	
////------------------------------- Creates ---------------------------------------	
//	
//	@Transactional
//	public Album createAlbum(String name, String description) {
//		em.clear();
//		Album a = new Album();
//		a.setName(name);
//		a.setDescription(description);
//		em.persist(a);
//		em.close();
//		return a;
//	}
//	
//	@Transactional
//	public Album createAlbum(String name) {
//		em.clear();
//		Album a = new Album();
//		a.setName(name);
//		em.persist(a);
//		em.close();
//		return a;
//	}
//
//	@Transactional
//	public Album createAlbum(Album a) {
//		em.clear();
//		em.persist(a);
//		em.close();
//		return a;
//	}
//
////------------------------------- Queries ---------------------------------------	
//	
//	//grabs all Albums in Album table
//	public List<Album> findAllAsList() {
//		List<Album> albumList = (List<Album>) em.createQuery("SELECT a FROM Album a ORDER BY a.name", Album.class).getResultList();
//		if(albumList.size()==0) {
//			System.out.println("AlbumService.findAllAsList: The results list was empty.");
//			return null;
//		} else {
//			return albumList;
//		}
//	}
//	
//	//grabs the Album with of certain name
//	public Album findAlbumByName(String name) {
//		@SuppressWarnings("unchecked")
//		List<Album> albumList = (List<Album>) em.createQuery("SELECT a FROM Album a WHERE a.name = :name").setParameter("name", name).getResultList();
//		if(albumList.size()==0) {
//			return null;
//		} else if(albumList.size()>1) {
//			System.out.println("AlbumService.findAlbumByName(): The results contained more than one item, the first item was returned.");
//			return albumList.get(0);
//		} else {
//			return albumList.get(0);
//		}
//
//	}
//	
//	//finds Album by Id
//	public Album findAlbumById(int id) {		
//		@SuppressWarnings("unchecked")
//		List<Album> albumList = (List<Album>) em.createQuery("SELECT a FROM Album a WHERE a.id = :id").setParameter("id", id).getResultList();
//		if(albumList.size()==0) {
//			return null;
//		} else if(albumList.size()>1) {
//			System.out.println("AlbumService.findAlbumById(): The results contained more than one item, the first item was returned.");
//			return albumList.get(0);
//		} else {
//			return albumList.get(0);
//		}
//	}
//	
////------------------------------- to String ---------------------------------------			
//	
//
//	
//}