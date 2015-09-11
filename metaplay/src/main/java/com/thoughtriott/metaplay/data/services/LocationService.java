package com.thoughtriott.metaplay.data.services;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.thoughtriott.metaplay.data.entities.Location;

public class LocationService {

	@PersistenceContext
	private EntityManager em;
	
	public LocationService () {
		//no-arg constructor
	}
	
	public Location createLocation(String city, String state) {
		Location l = new Location();
		l.setCity(city);
		l.setState(state);
		return l;
	}
	
	public Location findLocation() {
		
		Collection<Location> locations =  em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
		Location l = locations.iterator().next();
		
//		String query = "\"SELECT CITY FROM TABLE LOCATION where LOCATION.CITY = " + city + " and where state = " + state + "\"";
//		
//		Location l =  (Location) em.createQuery(query).getSingleResult();
//		System.out.println(l.getCity());
//		return l;
		
		return l;
		
//		Collection<Location> results = em.createQuery("SELECT CITY FROM TABLE LOCATION where LOCATION.CITY = :city", Location.class).setParameter("city", city).getResultList();
//		System.out.println("hello");
//		return results;
	}
}
