package com.thoughtriott.metaplay.data.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.thoughtriott.metaplay.data.entities.Member;

public class MemberService {

	@PersistenceContext
	private EntityManager em;
	
	public MemberService () {
		//no-arg constructor
	}
	
//------------------------------- SplitQueryCreate ---------------------------------------	
//takes a full name concatenated string, splits it into either two or three parts, and queries: if exists, returns, if not, creates and returns.
public Member splitQueryCreate(String fullName) {

	String[] nameArray = fullName.split(" ");
	
	if(nameArray.length == 2) {
		String firstName = nameArray[0];
		String lastName = nameArray[1];
		
		if(this.findMemberByName(lastName, firstName)!=null) {
			Member m = (Member) this.findMemberByName(lastName, firstName);
			return m;
		} else {
			Member m = (Member) this.createMember(lastName, firstName);
			return m;
		}
	} else {
		String firstName = nameArray[0];
		String middleName = nameArray[1];
		String lastName = nameArray[2];			
		
		if( this.findMemberByName(lastName, firstName, middleName)!=null) {
			Member m = (Member)  this.findMemberByName(lastName, firstName, middleName);
			return m;
		} else {
			Member m = (Member) this.createMember(lastName, firstName, middleName);
			return m;
		}
	}
}
	
//------------------------------- Creates ---------------------------------------	

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

	
//------------------------------- Queries ---------------------------------------	
	
	//grabs all Members in Member table
	public Collection<Member> findAllAsCollection() {
		return em.createQuery("SELECT m FROM Member m ORDER BY m.name", Member.class).getResultList();
	}
	
	//grabs the Member with of certain lastName, firstName
	public Member findMemberByName(String lastName, String firstName) {
		System.out.println("Remember, This method's parameters are: lastName, firstName.");
		return (Member) em.createQuery("SELECT m FROM Member m WHERE m.lastName = :lastName "
				+ "AND m.firstName = :firstName").setParameter("lastName", lastName).setParameter("firstName", firstName).getSingleResult();
	}

	//grabs the Member with of certain lastName
	public Member findMemberByName(String lastName) {
		System.out.println("Remember, This method's parameters are: lastName, firstName.");
		return (Member) em.createQuery("SELECT m FROM Member m WHERE m.lastName"
				+ " = :lastName").setParameter("lastName", lastName).getSingleResult();
	}
	
	//grabs the Member with lastName, firstName, middleName
	public Member findMemberByName(String lastName, String firstName, String middleName) {
		System.out.println("Remember, This method's parameters are: lastName, firstName.");
		return (Member) em.createQuery("SELECT m FROM Member m WHERE m.lastName = :lastName AND m.firstName = :firstName"
				+ " AND m.middleName = :middleName").setParameter("lastName", lastName).setParameter("firstName", firstName).setParameter("middleName", middleName).getSingleResult();
	}

	//finds Member by Id
	public Member findMemberById(int id) {
		return (Member) em.createQuery("SELECT m FROM Member m WHERE m.id = :id").setParameter("id", id).getSingleResult();
	}
	
//------------------------------- to String ---------------------------------------	
	
	//return a string of all of the cities in that state
	@SuppressWarnings("unchecked")
	public String findMembersByLastNameToString(String lastName) {
		List<Member> member = em.createQuery("SELECT m FROM Member m WHERE m.lastName = :name").setParameter("name", lastName).getResultList();
		int size = member.size();
		int indexCounter = 0;
		String memberString = "";
		
		Iterator<Member> it = member.iterator();
		while(it.hasNext()) {
			Member currentMember = it.next();
			indexCounter++;
			if (size==1) {
				memberString = "{" + currentMember.getFirstName() + " " + currentMember.getLastName() + "}";
			} else if(indexCounter < size && indexCounter != 1) {
				memberString = memberString + ", " + currentMember.getFirstName() + " " + currentMember.getLastName();
			} else if (indexCounter == size){
				memberString = memberString + ", " + currentMember.getFirstName() + " " + currentMember.getLastName() + "}";
			} else {
				memberString = "{" + currentMember.getFirstName() + " " + currentMember.getLastName();
			}
		}
		return memberString;
	}
	

}