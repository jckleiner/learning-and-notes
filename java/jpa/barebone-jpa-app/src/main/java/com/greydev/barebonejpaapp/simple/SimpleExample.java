package com.greydev.barebonejpaapp.simple;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class SimpleExample {

	public static void main(String[] args) {

		create();

		// read();

		// update();

		// delete();
	}


	private static void delete() {

		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// deleting ahmet
		User ahmet = entityManager.find(User.class, 2L);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.remove(ahmet);

		transaction.commit();

		entityManager.close();
		entityManagerFactory.close();

	}


	private static void update() {

		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// adds watermelon to ahmets favorite fruit list
		User ahmet = entityManager.find(User.class, 2L);
		Fruit watermelon = entityManager.find(Fruit.class, 4L);

		ahmet.addFavoriteFruit(watermelon);
		watermelon.addUser(ahmet);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(ahmet);
		entityManager.persist(watermelon);

		transaction.commit();

		entityManager.close();
		entityManagerFactory.close();

	}


	private static void read() {

		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		User user = entityManager.find(User.class, 1L);
		System.out.println(" ---> user: " + user);
	}


	private static void create() {

		User can = new User();
		User ahmet = new User();
		// Vehicle ferrari = new Vehicle();
		// Vehicle opel = new Vehicle();
		Fruit banana = new Fruit();
		Fruit watermelon = new Fruit();

		// can
		can.setName("can");
		// can.setVehicles(List.of(ferrari, opel));
		can.addFavoriteFruit(banana);
		can.addFavoriteFruit(watermelon);

		// ahmet
		ahmet.setName("ahmet");
		ahmet.addFavoriteFruit(banana);

		// ferrari
		// ferrari.setName("ferrari");

		// opel
		// opel.setName("opel");

		// ferrari.setUser(can);
		// opel.setUser(can);

		banana.setName("banana");
		banana.addUser(can);
		banana.addUser(ahmet);
		watermelon.setName("watermelon");
		watermelon.addUser(can);

		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(can);
		entityManager.persist(ahmet);
		// entityManager.persist(ferrari);
		// entityManager.persist(opel);
		entityManager.persist(banana);
		entityManager.persist(watermelon);

		transaction.commit();

		entityManager.close();
		entityManagerFactory.close();
	}
}
