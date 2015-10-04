package com.thoughtriott.metaplay.data.repositories;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.Location;

public class LocationRepositoryImpl implements LocationRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	LocationRepository locationRepository;
	
	//THIS METHOD SHOULD NOT BE NEEDED. IF I CAN DIRECTLY CAST TO LIST<STRING> FROM A DB QUERY THAN I SHOULDNT NEED THIS.
	//return a List of all of the states
	@Override
	public List<String> findAllStatesToString() {
		@SuppressWarnings("unchecked")
		List<String> locList = (List<String>) em.createQuery("SELECT DISTINCT l.state FROM Location l ORDER BY l.state").getResultList();
		if(locList.size()==0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return locList;
		}
	}
	
	//return a string of all of the cities formatted by state
	public String findAllToFormattedString() {
		List<String> states = locationRepository.findAllStatesToString();
		Iterator<String> it = states.iterator();
		String finalFormattedString = "";
		while(it.hasNext()) {
			String state = it.next();
			String citiesLocCommaSeparated = findCityByStateToString(state);
			finalFormattedString = finalFormattedString + state + ": " + citiesLocCommaSeparated + "\n";
		}
		return finalFormattedString;
	}

	//return a string of all of the cities in that state
	@Override
	public String findCityByStateToString(String state) {
		List<Location> locationList = locationRepository.findLocationByStateOrderByState(state);
		int size = locationList.size();
		int indexCounter = 0;
		String citiesString = "";
		
		Iterator<Location> it = locationList.iterator();
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
