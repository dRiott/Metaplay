package com.thoughtriott.metaplay.data.services;

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
	
//------------------------------- Creates ---------------------------------------	
	
	@Transactional
	public Location createLocation(String city, String state) {
		em.clear();
		Location l = new Location();
		l.setCity(city);
		l.setState(state);
		em.persist(l);
		em.close();
		return l;
	}
	
//------------------------------- Queries ---------------------------------------	
	
	//grabs all Locations in Location table
	public List<Location> findAllAsList() {
		List<Location> locList = (List<Location>) em.createQuery("SELECT l FROM Location l ORDER BY l.state, l.city", Location.class).getResultList();
		if(locList.size()==0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return locList;
		}
	}
	
	//grabs all the Locations belonging to a certain State
	@SuppressWarnings("unchecked")
	public List<Location> findLocationByState(String state) {
		List<Location> locList = (List<Location>) em.createQuery("SELECT l FROM Location l WHERE l.state = :state").setParameter("state", state).getResultList();
		if(locList.size()==0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return locList;
		}
	}
	
	//returns List<String> of each distinct State string, e.g. "Colorado", "California"
	public List<Location> findDistinctStates() {
		@SuppressWarnings("unchecked")
		List<Location> locList = (List<Location>) em.createQuery("SELECT DISTINCT l.state FROM Location l ORDER BY l.state").getResultList();
		if(locList.size()==0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return locList;
		}
	}
	
	//finds Location with City and State arguments
	@SuppressWarnings("unchecked")
	public Location findLocation(String city, String state) {
		List<Location> locList = (List<Location>) em.createQuery("SELECT l FROM Location l WHERE l.city = :city AND l.state = :state").setParameter("city", city).setParameter("state", state).getResultList();
		if(locList.size()==0) {
			return null;
		} else if(locList.size()>1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return locList.get(0);
		} else {
			return locList.get(0);
		}
	}
	
	//finds Location by Id
	public Location findLocationById(int id) {		
		@SuppressWarnings("unchecked")
		List<Location> locList = (List<Location>) em.createQuery("SELECT l FROM Location l WHERE l.id = :id").setParameter("id", id).getResultList();
		if(locList.size()==0) {
			return null;
		} else if(locList.size()>1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return locList.get(0);
		} else {
			return locList.get(0);
		}
	}
	
//------------------------------- to String ---------------------------------------				
	
	//returns List<String> of each distinct State string, e.g. "Colorado", "California"
		public List<String> findDistinctStatesToString() {
			@SuppressWarnings("unchecked")
			List<String> locList = (List<String>) em.createQuery("SELECT DISTINCT l.state FROM Location l ORDER BY l.state").getResultList();
			if(locList.size()==0) {
				System.out.println("The results list was empty.");
				return null;
			} else {
				return locList;
			}
		}
	
	//grabs all Locations and formats them into a nicely presented String
	public String findAllAsString() {
		List<String> states = findDistinctStatesToString();
		Iterator<String> it = states.iterator();
		String fullList = "";
		while(it.hasNext()) {
			String state = it.next();
			String citiesLoc = this.findStringCityByState(state);
			fullList = fullList + state + ": " + citiesLoc + "\n";
		}
		return fullList;
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

}