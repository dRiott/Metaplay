package com.thoughtriott.metaplay.data.services;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Member;

public class MemberService {

	@PersistenceContext
	private EntityManager em;

	public MemberService() {
		// no-arg constructor
	}

	// ------------------------------- SplitQueryCreate ---------------------------------------
	// takes a full name concatenated string, splits it into either two or three
	// parts, and queries: if exists, returns, if not, creates and returns.
	public Member splitQueryCreate(String fullName) {
		System.out.println("Inside the splitQueryCreate method");
		String[] nameArray = fullName.split(" ");
		System.out.println("Split successfully");
		if (nameArray.length != 0) {
			if(nameArray.length==1) {
				System.out.println("Inside if(nameArray.length == 1)...");
				String lastName = nameArray[0];
				if (this.findMemberByName(lastName) != null) {
					Member m = (Member) this.findMemberByName(lastName);
					return m;
				} else {
					System.out.println("About to call this.createMember() With One Argument");
					Member m = (Member) this.createMember(lastName);
					System.out.println("New member created because one did not already exist in database.");
					return m;
				}			
			} else if (nameArray.length == 2) {
				System.out.println("Inside if(nameArray.length == 2)...");
				String firstName = nameArray[0];
				String lastName = nameArray[1];
	
				if (this.findMemberByName(lastName, firstName) != null) {
					Member m = (Member) this.findMemberByName(lastName, firstName);
					return m;
				} else {
					System.out.println("About to call this.createMember() With Two Arguments");
					Member m = (Member) this.createMember(lastName, firstName);
					System.out.println("New member created because one did not already exist in database.");
					return m;
				}
			} else {
				String firstName = nameArray[0];
				String middleName = nameArray[1];
				String lastName = nameArray[2];
	
				if (this.findMemberByName(lastName, firstName, middleName) != null) {
					Member m = (Member) this.findMemberByName(lastName, firstName, middleName);
					return m;
				} else {
					System.out.println("About to call this.createMember() With Three Arguments");
					Member m = (Member) this.createMember(lastName, firstName, middleName);
					System.out.println("New member created because one did not already exist in database.");
					return m;
				}
			}
		} else {
			System.out.println("The argument was split into an empty array, could the argument be empty?");
			return null;
		}
	}

// ------------------------------- Creates ---------------------------------------

	@Transactional
	public Member createMember(String lastName, String firstName) {
		em.clear();
		Member m = new Member();
		m.setFirstName(firstName);
		m.setLastName(lastName);
		em.persist(m);
		return m;
	}

	@Transactional
	public Member createMember(String lastName, String firstName, String middleName) {
		em.clear();
		Member m = new Member();
		m.setFirstName(firstName);
		m.setMiddleName(middleName);
		m.setLastName(lastName);
		em.persist(m);
		return m;
	}

	@Transactional
	public Member createMember(String lastName) {
		em.clear();
		Member m = new Member();
		m.setLastName(lastName);
		em.persist(m);
		return m;
	}

	@Transactional
	public Member createMember(Member m) {
		em.clear();
		em.persist(m);
		return m;
	}

// ------------------------------- Queries ---------------------------------------

	// grabs all Members in Member table
	public List<Member> findAllAsList() {
		List<Member> memberList = (List<Member>) em.createQuery("SELECT m FROM Member m ORDER BY m.name", Member.class)
				.getResultList();
		if (memberList.size() == 0) {
			System.out.println("The results list was empty.");
			return null;
		} else {
			return memberList;
		}
	}

	// grabs the Member with of certain lastName, firstName
	public Member findMemberByName(String lastName, String firstName) {
		@SuppressWarnings("unchecked")
		List<Member> memberList = (List<Member>) em
				.createQuery("SELECT m FROM Member m WHERE m.lastName = :lastName " + "AND m.firstName = :firstName")
				.setParameter("lastName", lastName).setParameter("firstName", firstName).getResultList();
		if (memberList.size() == 0) {
			return null;
		} else if (memberList.size() > 1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return memberList.get(0);
		} else {
			return memberList.get(0);
		}

	}

	// grabs the Member with of certain lastName
	public Member findMemberByName(String lastName) {
		@SuppressWarnings("unchecked")
		List<Member> memberList = (List<Member>) em
				.createQuery("SELECT m FROM Member m WHERE m.lastName" + " = :lastName")
				.setParameter("lastName", lastName).getResultList();
		if (memberList.size() == 0) {
			return null;
		} else if (memberList.size() > 1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return memberList.get(0);
		} else {
			return memberList.get(0);
		}
	}

	// grabs the Member with lastName, firstName, middleName
	public Member findMemberByName(String lastName, String firstName, String middleName) {
		@SuppressWarnings("unchecked")
		List<Member> memberList = (List<Member>) em
				.createQuery("SELECT m FROM Member m WHERE m.lastName =" + " :lastName AND m.firstName = :firstName"
						+ " AND m.middleName = :middleName")
				.setParameter("lastName", lastName).setParameter("firstName", firstName)
				.setParameter("middleName", middleName).getResultList();
		if (memberList.size() == 0) {
			return null;
		} else if (memberList.size() > 1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return memberList.get(0);
		} else {
			return memberList.get(0);
		}
	}

	// finds Member by Id
	public Member findMemberById(int id) {
		@SuppressWarnings("unchecked")
		List<Member> memberList = (List<Member>) em.createQuery("SELECT m FROM Member m WHERE m.id = :id")
				.setParameter("id", id).getResultList();
		if (memberList.size() == 0) {
			return null;
		} else if (memberList.size() > 1) {
			System.out.println("Results list contained more than one item, the first item was returned.");
			return memberList.get(0);
		} else {
			return memberList.get(0);
		}
	}

// ------------------------------- to String ---------------------------------------

	// return a string of all of the Members with a certain name
	@SuppressWarnings("unchecked")
	public String findMembersByLastNameToString(String lastName) {
		List<Member> member = em.createQuery("SELECT m FROM Member m WHERE m.lastName = :name")
				.setParameter("name", lastName).getResultList();
		int size = member.size();
		int indexCounter = 0;
		String memberString = "";

		Iterator<Member> it = member.iterator();
		while (it.hasNext()) {
			Member currentMember = it.next();
			indexCounter++;
			if (size == 1) {
				memberString = "{" + currentMember.getFirstName() + " " + currentMember.getLastName() + "}";
			} else if (indexCounter < size && indexCounter != 1) {
				memberString = memberString + ", " + currentMember.getFirstName() + " " + currentMember.getLastName();
			} else if (indexCounter == size) {
				memberString = memberString + ", " + currentMember.getFirstName() + " " + currentMember.getLastName()
						+ "}";
			} else {
				memberString = "{" + currentMember.getFirstName() + " " + currentMember.getLastName();
			}
		}
		return memberString;
	}

}