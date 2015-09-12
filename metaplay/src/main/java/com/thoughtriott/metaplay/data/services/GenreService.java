package com.thoughtriott.metaplay.data.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Genre;

public class GenreService {

	@PersistenceContext
	private EntityManager em;
	
	public GenreService () {
		//no-arg constructor
	}
	
	@Transactional
	public Genre createGenre(String name, String description) {
		em.clear();
		Genre g = new Genre();
		g.setName(name);
		g.setDescription(description);
		em.persist(g);
		return g;
	}
	
	//grabs all Locations in Location table
	public Collection<Genre> findAllAsCollection() {
		return em.createQuery("SELECT g FROM Genre g ORDER BY g.name", Genre.class).getResultList();
	}
	
	//grabs all the Locations belonging to a certain State
	@SuppressWarnings("unchecked")
	public List<Genre> findListGenreByName(String name) {
		return em.createQuery("SELECT g FROM Genre g WHERE g.name = :name").setParameter("name", name).getResultList();

	}
	
	//return a string of all of the cities in that state
	@SuppressWarnings("unchecked")
	public String findGenreByName(String name) {
		List<Genre> genre = em.createQuery("SELECT g FROM Genre g WHERE g.name = :name").setParameter("name", name).getResultList();
		int size = genre.size();
		int indexCounter = 0;
		String genresString = "";
		
		Iterator<Genre> it = genre.iterator();
		while(it.hasNext()) {
			Genre currentGenre = it.next();
			indexCounter++;
			if (size==1) {
				genresString = "{" + currentGenre.getName() + "}";
			} else if(indexCounter < size && indexCounter != 1) {
				genresString = genresString + ", " + currentGenre.getName();
			} else if (indexCounter == size){
				genresString = genresString + ", " + currentGenre.getName() + "}";
			} else {
				genresString = "{" + currentGenre.getName();
			}
		}
		return genresString;
	}
	
	//finds Location by Id
	public Genre findGenreById(int id) {
		return (Genre) em.createQuery("SELECT g FROM Genre g WHERE g.id = :id").setParameter("id", id).getSingleResult();
	}

}