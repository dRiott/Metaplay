package com.thoughtriott.metaplay.data.repositories;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.Member;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public Member setNameFromArray(String[] nameArray) {
		if (nameArray.length != 0) {
			if (nameArray.length == 1) {
				String lastName = nameArray[0];
				if (memberRepository.findMemberByLastNameIsNotNull(lastName)) {
					if(memberRepository.findMemberByLastName(lastName).size()>1)
						System.out.println("List<Member> contained more than one result. Returning the first one.");
					return memberRepository.findMemberByLastName(lastName).get(0);
				} else {
					System.out.println("MemberRepositoryImpl:setNameFromArray - findMemberByLastName was null, returning new Member with lastname set, not yet persisted.");
					return new Member(lastName);
				}
			} else if (nameArray.length == 2) {
				System.out.println("Inside setNameFromArray method: if(nameArray.length == 2)...");
				String firstName = nameArray[0];
				String lastName = nameArray[1];

				if (memberRepository.findMemberByLastNameAndFirstNameIsNotNull(lastName, firstName)) {
					if(memberRepository.findMemberByLastNameAndFirstName(lastName, firstName).size()>1)
						System.out.println("List<Member> contained more than one result. Returning the first one.");
					return memberRepository.findMemberByLastNameAndFirstName(lastName, firstName).get(0);
				} else {
					System.out.println("MemberRepositoryImpl:setNameFromArray - findMemberByLastNameAndFirstName was null, returning new Member with firstName and lastName set, not yet persisted.");
					return new Member(lastName, firstName);
				}
			} else if (nameArray.length == 3) {
				System.out.println("Inside setNameFromArray method: if(nameArray.length == 3)...");
				String firstName = nameArray[0];
				String middleName = nameArray[1];
				String lastName = nameArray[2];

				if (memberRepository.findMemberByLastNameAndFirstNameAndMiddleNameIsNotNull(lastName, firstName, middleName)) {
					if(memberRepository.findMemberByLastNameAndFirstNameAndMiddleName(lastName, firstName, middleName).size()>1)
						System.out.println("List<Member> contained more than one result. Returning the first one.");
					return memberRepository.findMemberByLastNameAndFirstNameAndMiddleName(lastName, firstName, middleName).get(0);
				} else {
					System.out.println("MemberRepositoryImpl:setNameFromArray - findMemberByLastNameAndFirstNameAndMiddleName was null, returning new Member with firstName, middleName and lastName set, not yet persisted.");
					return new Member(lastName, firstName, middleName);
				}
			} else {
				System.out.println("The nameArray argument had more than 3 indexes, and only supports 3 (first name, middle name, last name)..."
						+ "returning null.");
				return null;
			}
		} else {
			System.out.println("The nameArray argument was empty, returning null.");
			return null;
		}
	}
	
	@Override
	public String[] splitFullName(String fullName) {
		System.out.println("MemberRepositoryImpl - splitFullName()");
		return fullName.split(" ");
	}
	
	@Override
	public String findMemberByLastNameToFormattedString(String lastName) {
		List<Member> members = memberRepository.findMemberByLastName(lastName);
		int indexCounter = 0;
		String memberString = "";

		Iterator<Member> it = members.iterator();
		while (it.hasNext()) {
			Member currentMember = it.next();
			indexCounter++;
			if (members.size()== 1) {
				memberString = "{" + currentMember.getFirstName() + " " + currentMember.getLastName() + "}";
			} else if (indexCounter < members.size() && indexCounter != 1) {
				memberString = memberString + ", " + currentMember.getFirstName() + " " + currentMember.getLastName();
			} else if (indexCounter == members.size()) {
				memberString = memberString + ", " + currentMember.getFirstName() + " " + currentMember.getLastName()
						+ "}";
			} else {
				memberString = "{" + currentMember.getFirstName() + " " + currentMember.getLastName();
			}
		}
		return memberString;
	}

}
