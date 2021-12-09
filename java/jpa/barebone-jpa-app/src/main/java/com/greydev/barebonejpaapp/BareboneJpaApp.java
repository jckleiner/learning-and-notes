package com.greydev.barebonejpaapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class BareboneJpaApp {

	public static void main(String[] args) {
		Employee employee = new Employee(1, "Can");
		Employee employee2 = new Employee(2, "Ahmet");

		// creates an EntityManagerFactory using your persistence.xml config
		// we need to give the name of the persistence-unit we defined: "myApp"
		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// saves both instances as rows in the table
		entityManager.persist(employee);
		entityManager.persist(employee2);

		transaction.commit();
	}
}

