package com.thoughtriott.metaplay.data.repositories.jpa;

import com.thoughtriott.metaplay.data.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer>, PlaylistRepositoryCustom {

	//this is where you write Spring Data Queries to be read and interpreted by Spring
	List<Playlist> findPlaylistByName(String name);
	List<Playlist> findPlaylistByNameOrderByName(String name);

    @Query("SELECT p FROM Playlist p JOIN FETCH p.tracks WHERE p.id = (:id)")
    public Playlist findByIdAndFetchTracksEagerly(@Param("id") Integer id);
}
	

// http://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
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

