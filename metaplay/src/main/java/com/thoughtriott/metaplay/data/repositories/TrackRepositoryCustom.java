package com.thoughtriott.metaplay.data.repositories;

import java.util.List;

public interface TrackRepositoryCustom {
	
	//custom methods are declared here, implemented in ***RepositoryImpl, and finally extended by the ***Repository.
	List<String> findAllToListString();
	String findAllToFormattedString();
	
}
