package com.thoughtriott.metaplay.data.repositories.jpa;

import java.util.List;

public interface LocationRepositoryCustom {
	List<String> findAllStatesToListString();
	List<String> findAllCountriesToListString();
	String findCityByStateToString(String state);
	String findAllToFormattedString();
	
}
