package com.thoughtriott.metaplay.data.repositories;

import java.util.List;

public interface LocationRepositoryCustom {
	List<String> findAllStatesToString();
	String findCityByStateToString(String state);
	String findAllToFormattedString();
	
}
