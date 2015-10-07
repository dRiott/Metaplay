package com.thoughtriott.metaplay.data.repositories;

import java.util.List;

public interface AccountRepositoryCustom {
	
	//custom methods are declared here, implemented in ***RepositoryImpl, and finally extended by the ***Repository.
	String findAllToFormattedString();
	List<String> findAllToListString();
}
