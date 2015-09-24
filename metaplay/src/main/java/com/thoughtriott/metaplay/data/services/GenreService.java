package com.thoughtriott.metaplay.data.services;

import java.util.Iterator;
import java.util.LinkedList;
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
	
//------------------------------- Creates ---------------------------------------		
	
	@Transactional
	public Genre createGenre(String name, String description) {
		em.clear();
		Genre g = new Genre();
		g.setName(name);
		g.setDescription(description);
		em.persist(g);
		em.close();
		return g;
	}
	
	@Transactional
	public Genre createGenre(String name) {
		em.clear();
		Genre g = new Genre();
		g.setName(name);
		em.persist(g);
		em.close();
		return g;
	}
	
//------------------------------- Queries ---------------------------------------	

	//grabs all Genres in Genre table
	public List<Genre> findAllAsList() {		
		 List<Genre> genreList = (List<Genre>) em.createQuery("SELECT g FROM Genre g ORDER BY g.name", Genre.class).getResultList();
			if(genreList.size()==0) {
				System.out.println("The results list was empty.");
				return null;
			} else {
				return genreList;
			}
	}
	
	//grabs the Genre with of certain name
	public Genre findGenreByName(String name) {
		@SuppressWarnings("unchecked")
		List<Genre> genreList = (List<Genre>) em.createQuery("SELECT g FROM Genre g WHERE g.name = :name").setParameter("name", name).getResultList();
		if(genreList.size()==0) {
			return null;
		} else if(genreList.size()>1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return genreList.get(0);
		} else {
			return genreList.get(0);
		}
	}
	
	//finds Genre by Id
	public Genre findGenreById(int id) {
		@SuppressWarnings("unchecked")
		List<Genre> genreList = (List<Genre>) em.createQuery("SELECT g FROM Genre g WHERE g.id = :id").setParameter("id", id).getResultList();
		if(genreList.size()==0) {
			return null;
		} else if(genreList.size()>1) {
			System.out.println("The results contained more than one item, the first item was returned.");
			return genreList.get(0);
		} else {
			return genreList.get(0);
		}	
	}
	
//------------------------------- to String ---------------------------------------			

	
	//returns List<String> of each Genre
		public List<String> findAllAsListString() {
			@SuppressWarnings("unchecked")
			List<Genre> genreList = (List<Genre>) em.createQuery("SELECT g FROM Genre g ORDER BY g.name").getResultList();
			Iterator<Genre> it = genreList.iterator();
			List<String> genStrList = new LinkedList<String>();
			while(it.hasNext()) {
				Genre genre = it.next();
				genStrList.add(genre.getName());
			}
			if(genreList.size()==0) {
				System.out.println("The results list was empty.");
				return null;
			} else {
				genStrList.add("Other");
				return genStrList;
			}
		}
	
	//return a string of all of the Genres with a certain name
	@SuppressWarnings("unchecked")
	public String findGenreByNameToString(String name) {
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
	


}