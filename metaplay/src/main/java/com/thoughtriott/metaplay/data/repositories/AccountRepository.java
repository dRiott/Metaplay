package com.thoughtriott.metaplay.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thoughtriott.metaplay.data.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>, AccountRepositoryCustom {

	//this is where you write Spring Data Queries to be read and interpreted by Spring
	
	List<Account> findAccountByAccountname(String accountname);
	
}



//http://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
	// By extending JpaRepository, inherits 18 methods for performing common persistence operations
	// Spring Data implements these for us with the @EnableJpaRepositories("com.thoughtriott.metaplay.data.respositories")
	// This repository transitively extends Repository interface through JpaRepository... generated at application startup time
	
	// ***** METHODS GIVEN TO YOU *****
		//     - List<T> findAll()
		//     - List<T> findAll(Iterable<ID> ids)
		//     - List<T> findAll(Sort sort)
		//     - getOne: Returns a reference to the entity with the given identifier.
		//     - saveAndFlush(S entity) : Saves an entity and flushes changes instantly. Returns the saved entity
		//     - save(Iterable<S> entities) 
		//     - flush(): Flushes all pending changes to the database.
		//     - deleteAllInBatch() : Deletes all entites in a batch call.

