package com.thoughtriott.metaplay.data.repositories.jpa;

import java.util.List;

public interface LocationRepositoryCustom {
	List<String> findAllStatesToListString();
	String findCityByStateToString(String state);
	String findAllToFormattedString();
	
}
