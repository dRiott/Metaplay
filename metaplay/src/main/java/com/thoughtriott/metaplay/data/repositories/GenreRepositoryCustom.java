package com.thoughtriott.metaplay.data.repositories;

import java.util.List;

public interface GenreRepositoryCustom {
	
	//custom methods are declared here but implemented in ***RepositoryImpl, and finally extended by the ***Repository.
	String findGenreByNameToString(String name);
	List<String> findAllToFormattedString();
	
}
