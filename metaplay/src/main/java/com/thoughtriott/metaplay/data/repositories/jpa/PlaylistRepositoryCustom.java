package com.thoughtriott.metaplay.data.repositories.jpa;

public interface PlaylistRepositoryCustom {
	
	//custom methods are declared here, implemented in ***RepositoryImpl, and finally extended by the ***Repository.
	String findPlaylistByNameToFormattedString(String name);
}
