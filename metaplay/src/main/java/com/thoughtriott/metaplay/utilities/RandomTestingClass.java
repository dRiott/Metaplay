package com.thoughtriott.metaplay.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.thoughtriott.metaplay.data.entities.Artist;

public class RandomTestingClass {
	public static void main(String[] args) {

//		FileSystemXmlApplicationContext context=new FileSystemXmlApplicationContext("webapp/WEB-INF/web.xml");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MetaPlayPersistence");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Artist a = em.find(Artist.class, 3);

			System.out.println(a);

		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			emf.close();
		}
		System.out.println("Transaction finished, yo.");

	}
}