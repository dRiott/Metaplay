package com.thoughtriott.metaplay.data.repositories.jpa;

import java.util.List;

public interface RecordLabelRepositoryCustom {
	
	//custom methods are declared here, implemented in ***RepositoryImpl, and finally extended by the ***Repository.
	List<String> findAllAsListString();
	String findRecordLabelsByNameToString(String name);
	
}
