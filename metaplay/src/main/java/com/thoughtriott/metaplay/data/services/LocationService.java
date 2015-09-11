package com.thoughtriott.metaplay.data.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Location;

public class LocationService {

	@PersistenceContext
	private EntityManager em;
	
	public LocationService () {
		//no-arg constructor
	}
	
	@Transactional
	public Location createLocation(String city, String state) {
		em.clear();
		
		Location l = new Location();
		l.setCity(city);
		l.setState(state);
		
		em.persist(l);
		
		return l;
	}
	
	//grabs all Locations in Location table
	public Collection<Location> findAllAsCollection() {
		return em.createQuery("SELECT l FROM Location l ORDER BY l.state, l.city", Location.class).getResultList();
	}
	
	//grabs all Locations and formats them into a nicely presented String
	public String findAllAsString() {
		List<String> states = findDistinctStates();
		Iterator<String> it = states.iterator();
		String fullList = "";
		while(it.hasNext()) {
			String state = it.next();
			String citiesLoc = this.findStringCityByState(state);
			fullList = fullList + state + ": " + citiesLoc + "\n";
		}
		return fullList;
	}
	
	//grabs all the Locations belonging to a certain State
	@SuppressWarnings("unchecked")
	public List<Location> findLocationByState(String state) {
		return em.createQuery("SELECT l FROM Location l WHERE l.state = :state").setParameter("state", state).getResultList();

	}
	
	//returns List<String> of each distinct State string, e.g. "Colorado", "California"
	@SuppressWarnings("unchecked")
	public List<String> findDistinctStates() {
		return em.createQuery("SELECT DISTINCT l.state FROM Location l ORDER BY l.state").getResultList();

	}
	
	//return a string of all of the cities in that state
	@SuppressWarnings("unchecked")
	public String findStringCityByState(String state) {
		List<Location> location = em.createQuery("SELECT l FROM Location l WHERE l.state = :state").setParameter("state", state).getResultList();
		int size = location.size();
		int indexCounter = 0;
		String citiesString = "";
		
		Iterator<Location> it = location.iterator();
		while(it.hasNext()) {
			Location currentLocation = it.next();
			indexCounter++;
			if (size==1) {
				citiesString = "{" + currentLocation.getCity() + "}";
			} else if(indexCounter < size && indexCounter != 1) {
				citiesString = citiesString + ", " + currentLocation.getCity();
			} else if (indexCounter == size){
				citiesString = citiesString + ", " + currentLocation.getCity() + "}";
			} else {
				citiesString = "{" + currentLocation.getCity();
			}
		}
		return citiesString;
	}
	
	//finds Location with City and State arguments
	public Location findLocation(String city, String state) {
		return (Location) em.createQuery("SELECT l FROM Location l WHERE l.city = :city AND l.state = :state").setParameter("city", city).setParameter("state", state).getSingleResult();
	}
	
	//finds Location by Id
	public Location findLocationById(int id) {
		return (Location) em.createQuery("SELECT l FROM Location l WHERE l.id = :id").setParameter("id", id).getSingleResult();
	}

}