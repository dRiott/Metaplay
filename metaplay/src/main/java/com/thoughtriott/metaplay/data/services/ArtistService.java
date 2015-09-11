package com.thoughtriott.metaplay.data.services;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.thoughtriott.metaplay.data.entities.Artist;

public class ArtistService {

	@PersistenceContext
	private EntityManager em;
	
	public ArtistService () {
		//no-arg constructor
	}
	
	public Artist createArtist(String name, String genre, String recordLabel, String location) {
		em.clear();
		em.getTransaction().begin();
		Artist a = new Artist();
		a.setName(name);
		em.persist(a);
		em.close();
		return a;
	}
	
	public Collection<Artist> findAllArtists() {
		 return em.createQuery("SELECT a FROM Artist a ORDER BY a.name", Artist.class).getResultList();
	}
	
	public Artist findArtistByName(String name) {
		return (Artist) em.createQuery("SELECT a FROM Artist a WHERE a.name = :name").setParameter("name", name).getSingleResult();
	}
	
	public Artist findArtistById(int id) {
		return (Artist) em.createQuery("SELECT a FROM Artist a WHERE l.id = :id").setParameter("id", id).getSingleResult();
	}

}