package com.thoughtriott.metaplay.data.services;

import java.util.Collection;
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
	
	@Transactional
	public RecordLabel createRecordLabel(String name, Location location) {
		em.clear();
		RecordLabel r = new RecordLabel();
		r.setLocation(location);
		r.setName(name);
		em.persist(r);
		return r;
	}
	
	//grabs all Locations in Location table
	public Collection<RecordLabel> findAllAsCollection() {
		return em.createQuery("SELECT r FROM RecordLabel r ORDER BY r.name", RecordLabel.class).getResultList();
	}
	
	//grabs all the Locations belonging to a certain State
	@SuppressWarnings("unchecked")
	public List<RecordLabel> findRecordLabelByName(String name) {
		return em.createQuery("SELECT r FROM RecordLabel r WHERE r.name = :name").setParameter("name", name).getResultList();

	}
	
	//finds Location by Id
	public RecordLabel findRecordLabelById(int id) {
		return (RecordLabel) em.createQuery("SELECT r FROM RecordLabel r WHERE r.id = :id").setParameter("id", id).getSingleResult();
	}

}