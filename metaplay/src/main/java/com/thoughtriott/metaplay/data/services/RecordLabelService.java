package com.thoughtriott.metaplay.data.services;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Location;
import com.thoughtriott.metaplay.data.entities.RecordLabel;

public class RecordLabelService {

	@PersistenceContext
	private EntityManager em;
	
	public RecordLabelService () {
		//no-arg constructor
	}
	
// ------------------------------- Creates ---------------------------------------
	
	@Transactional
	public RecordLabel createRecordLabel(String name, Location location) {
		em.clear();
		RecordLabel r = new RecordLabel();
		r.setLocation(location);
		r.setName(name);
		em.persist(r);
		return r;
	}
	
//------------------------------- Queries ---------------------------------------
	
	//grabs all Locations in Location table
	public List<RecordLabel> findAllAsList() {
		List<RecordLabel> recordLabelList = (List<RecordLabel>) em.createQuery("SELECT r FROM RecordLabel r ORDER BY r.name", RecordLabel.class)
				.getResultList();
		if (recordLabelList.size() == 0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return recordLabelList;
		}
	
	}
	
	//grabs all the Locations belonging to a certain State
	public RecordLabel findRecordLabelByName(String name) {
		@SuppressWarnings("unchecked")
		List<RecordLabel> recordLabelList = (List<RecordLabel>) em.createQuery("SELECT r FROM RecordLabel r WHERE r.name = :name").setParameter("name", name).getResultList();
		if (recordLabelList.size() == 0) {
			return null;
		} else if (recordLabelList.size() > 1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return recordLabelList.get(0);
		} else {
			return recordLabelList.get(0);
		}
	}
	
	//finds Location by Id
	public RecordLabel findRecordLabelById(int id) {
		@SuppressWarnings("unchecked")
		List<RecordLabel> recordLabelList = (List<RecordLabel>) em.createQuery("SELECT r FROM RecordLabel r WHERE r.id = :id").setParameter("id", id).getResultList();
		if (recordLabelList.size() == 0) {
			return null;
		} else if (recordLabelList.size() > 1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return recordLabelList.get(0);
		} else {
			return recordLabelList.get(0);
		}
	}
	
// ------------------------------- to String ---------------------------------------

	//returns List<String> of each RecordLabel
	public List<String> findAllAsListString() {
		@SuppressWarnings("unchecked")
		List<RecordLabel> recordLabelList = (List<RecordLabel>) em.createQuery("SELECT rl FROM RecordLabel rl ORDER BY rl.name").getResultList();
		Iterator<RecordLabel> it = recordLabelList.iterator();
		List<String> recLblStrList = new LinkedList<String>();
		while(it.hasNext()) {
			RecordLabel rl = it.next();
			recLblStrList.add(rl.getName());
		}
		if(recordLabelList.size()==0) {
			System.out.println("The results list was empty.");
			recLblStrList.add("No Artists exist, add one!");
			recLblStrList.add("** New Record Label **");
			return recLblStrList;
		} else {
			recLblStrList.add("** New Record Label **");
			return recLblStrList;
		}
	}
	
	// return a string of all of the RecordLabels with a certain name
	@SuppressWarnings("unchecked")
	public String findRecordLabelsByNameToString(String name) {
		List<RecordLabel> recordLabelsList = em.createQuery("SELECT rl FROM RecordLabel rl WHERE rl.name = :name")
				.setParameter("name", name).getResultList();
		int size = recordLabelsList.size();
		int indexCounter = 0;
		String recordLabelString = "";

		Iterator<RecordLabel> it = recordLabelsList.iterator();
		while (it.hasNext()) {
			RecordLabel currentRecordLabel = it.next();
			indexCounter++;
			if (size == 1) {
				recordLabelString = "{" + currentRecordLabel.getName() + "}";
			} else if (indexCounter < size && indexCounter != 1) {
				recordLabelString = recordLabelString + ", " + currentRecordLabel.getName();
			} else if (indexCounter == size) {
				recordLabelString = recordLabelString + ", " + currentRecordLabel.getName() + "}";
			} else {
				recordLabelString = "{" + currentRecordLabel.getName();
			}
		}
		return recordLabelString;
	}


}