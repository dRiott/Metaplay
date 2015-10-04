package com.thoughtriott.metaplay.data.repositories;
import com.thoughtriott.metaplay.data.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepositoryInterface extends JpaRepository<Artist, Integer>  {
	
	//by extending JpaRepository, inherits 18 methods for performing common persistence operations
	//Spring Data implements these for us with the @EnableJpaRepositories("com.thoughtriott.metaplay.data.respositories")
	//ArtistRepositoryInterface transitively extends Repository interface through JpaRepository... generated at application startup time.23
}
