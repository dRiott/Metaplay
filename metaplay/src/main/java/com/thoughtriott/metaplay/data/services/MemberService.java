package com.thoughtriott.metaplay.data.services;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Member;

public class MemberService {

	@PersistenceContext
	private EntityManager em;

	public MemberService() {
		// no-arg constructor
	}

// ------------------------------- Proprietary Methods ---------------------------------------
	// takes a String array uses indexes to query members table: if exists, returns, if not, creates and returns.
	@Transactional
	public Member createFromNameArray(String[] nameArray) {
		if (nameArray.length != 0) {
			if (nameArray.length == 1) {
				System.out.println("Inside if(nameArray.length == 1)...");
				String lastName = nameArray[0];
				if (this.findMemberByName(lastName) != null) {
					return (Member) this.findMemberByName(lastName);
				} else {
					System.out.println("About to call this.createMember() With One Argument");
					this.createMember(lastName);
					System.out.println("New member created because one did not already exist in database.");
					return (Member) this.findMemberByName(lastName);
				}
			} else if (nameArray.length == 2) {
				System.out.println("Inside if(nameArray.length == 2)...");
				String firstName = nameArray[0];
				String lastName = nameArray[1];

				if (this.findMemberByName(lastName, firstName) != null) {
					return (Member) this.findMemberByName(lastName, firstName);
				} else {
					System.out.println("About to call this.createMember() With Two Arguments");
					this.createMember(lastName, firstName);
					System.out.println("New member created because one did not already exist in database.");
					return (Member) this.findMemberByName(lastName, firstName);
				}
			} else if (nameArray.length == 3) {
				String firstName = nameArray[0];
				String middleName = nameArray[1];
				String lastName = nameArray[2];

				if (this.findMemberByName(lastName, firstName, middleName) != null) {
					return (Member) this.findMemberByName(lastName, firstName, middleName);
				} else {
					System.out.println("About to call this.createMember() With Three Arguments");
					this.createMember(lastName, firstName, middleName);
					System.out.println("New member created because one did not already exist in database.");
					return this.findMemberByName(lastName, firstName, middleName);
				}
			} else {
				System.out.println("The array argument had more than 3 indexes, and only supports 3 (first name, middle name, last name)..."
						+ "returning null.");
				return null;
			}
		} else {
			System.out.println("The array argument was empty, returning null.");
			return null;
		}
	}

	public String[] splitFullName(String fullName) {
		System.out.println("Inside the splitQueryCreate method");
		String[] nameArray = fullName.split(" ");
		System.out.println("Split successfully");
		return nameArray;
	}

	// ------------------------------- Creates
	// ---------------------------------------

	@Transactional("transactionManager")
	public Member createMember(String lastName, String firstName) {
		em.clear();
		Member m = new Member();
		m.setFirstName(firstName);
		m.setLastName(lastName);
		em.persist(m);
		em.close();
		return m;
	}

	@Transactional("transactionManager")
	public Member createMember(String lastName, String firstName, String middleName) {
		System.out.println("Inside the createMember method.");
		em.clear();
		Member m = new Member();
		m.setFirstName(firstName);
		m.setMiddleName(middleName);
		m.setLastName(lastName);
		System.out.println("Just before persist");
		em.persist(m);
		System.out.println("After before persist");
		em.close();
		return m;
	}

	@Transactional
	public Member createMember(String lastName) {
		em.clear();
		Member m = new Member();
		m.setLastName(lastName);
		em.persist(m);
		em.close();
		return m;
	}

	@Transactional
	public Member createMember(Member m) {
		em.clear();
		em.persist(m);
		em.close();
		return m;
	}

	// ------------------------------- Queries
	// ---------------------------------------

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