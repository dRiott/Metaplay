package com.thoughtriott.metaplay.data.repositories.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thoughtriott.metaplay.data.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

	List<Request> findRequestByName(String name);
	
}