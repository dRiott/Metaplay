package com.thoughtriott.metaplay.data.repositories.jpa;

import com.thoughtriott.metaplay.data.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public Member setNameFromArray(String[] nameArray) {
		System.out.println("inside setNameFromArray");
		if (nameArray!=null && nameArray.length != 0) {
			System.out.println("Inside nameArray!=null && nameArray.length != 0");
			if (nameArray.length == 1) {
				List<Member> members = memberRepository.findMemberByLastName(nameArray[0]);
				System.out.println("Inside nameArray.length ==1");
				if (members!=null && members.size()>0) {
					System.out.println("members!=null");
					if(members.size()>1) {
						System.out.println("List<Member> contained more than one result. Returning the first one.");
						return members.get(0);
					} else {
						System.out.println("inside else");
						return members.get(0);
					}
				} else {
					System.out.println("MemberRepositoryImpl:setNameFromArray - findMemberByLastName was null, returning new Member with lastname set, not yet persisted.");
					return new Member(nameArray[0]);
				}
			} else if (nameArray.length == 2) {
				System.out.println("Inside setNameFromArray method: if(nameArray.length == 2)...");
				List<Member> members = memberRepository.findMemberByLastNameAndFirstName(nameArray[1], nameArray[0]);
				if (members!=null && members.size()>0) {
					if(members.size()>1) {
						System.out.println("List<Member> contained more than one result. Returning the first one.");
						return members.get(0);
					} else {
						System.out.println("MemberRepositoryImpl:setNameFromArray - findMemberByLastNameAndFirstName: MATCH FOUND, RETURNING THE MATCH");
						return members.get(0);
					}
				} else {
					System.out.println("MemberRepositoryImpl:setNameFromArray - findMemberByLastNameAndFirstName was null, returning new Member with firstName and lastName set, not yet persisted.");
					return new Member(nameArray[1], nameArray[0]);
				}
			} else if (nameArray.length == 3) {
				System.out.println("Inside setNameFromArray method: if(nameArray.length == 3)...");
				List<Member> members = memberRepository.findMemberByLastNameAndFirstNameAndMiddleName(nameArray[2], nameArray[0], nameArray[1]);
				if (members!=null && members.size()>0) {
					if(members.size()>1) {
						System.out.println("List<Member> contained more than one result. Returning the first one.");
						return members.get(0);
					} else {
						return members.get(0);
					}
				} else {
					System.out.println("MemberRepositoryImpl:setNameFromArray - findMemberByLastNameAndFirstNameAndMiddleName was null, returning new Member with firstName, middleName and lastName set, not yet persisted.");
					return new Member(nameArray[2], nameArray[0], nameArray[1]);
				}
			} else {
				System.out.println("The nameArray argument had more than 3 indexes, using first two as First and Middle, and the final item in the Array as the Last.");
				return new Member(nameArray[nameArray.length-1], nameArray[0], nameArray[1]);
			}
		}

	System.out.println("Something went wrong, and no other returns happened... returning null.");
	return null;

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
