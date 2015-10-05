package com.thoughtriott.metaplay.data.repositories;

import com.thoughtriott.metaplay.data.entities.Member;

public interface MemberRepositoryCustom {
	
	//custom methods are declared here, implemented in ***RepositoryImpl, and finally extended by the ***Repository.
	Member setNameFromArray(String[] nameArray);
	String[] splitFullName(String fullName);
	String findMemberByLastNameToFormattedString(String lastName);
	
}
