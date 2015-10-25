package com.thoughtriott.metaplay.data.repositories.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.Location;

public class LocationRepositoryImpl implements LocationRepositoryCustom {
	
	@Autowired
	LocationRepository locationRepository;
	
	//returns List<String> of each State
	@Override
	public List<String> findAllStatesToListString() {
		List<Location> locList = locationRepository.findAll();
		Iterator<Location> it = locList.iterator();
		List<String> stateStringList = new ArrayList<String>();
		while(it.hasNext()) {
			Location l = it.next();
			stateStringList.add(l.getState());
		}
		if(locList.size()==0) {
			return null;
		} else {
			return stateStringList;
		}
	}
	
	//returns List<String> of each Country, adding "** New Country **" to the List.
	@Override
	public List<String> findAllCountriesToListString() {
		List<Location> locList = locationRepository.findAll();
		Iterator<Location> it = locList.iterator();
		List<String> countryStringList = new LinkedList<String>();
		while(it.hasNext()) {
			Location l = it.next();
			if(!countryStringList.contains(l.getCountry())) {
				countryStringList.add(l.getCountry());
			}
		}
		countryStringList.add("** New Country **");
		return countryStringList;
	}	
	
	
	//return a string of all of the cities formatted by state
	@Override
	public String findAllToFormattedString() {
		List<String> states = locationRepository.findAllStatesToListString();
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
