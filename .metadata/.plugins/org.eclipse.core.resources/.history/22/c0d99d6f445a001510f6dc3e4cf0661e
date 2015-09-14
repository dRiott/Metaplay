package com.thoughtriott.metaplay.data.services;

import java.util.Collection;

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