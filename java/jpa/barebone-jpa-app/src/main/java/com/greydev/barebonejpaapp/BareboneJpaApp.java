package com.greydev.barebonejpaapp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class BareboneJpaApp {

	public static void main(String[] args) {

		// persistEmployees();

		read();
	}


	private static void executeTransaction(Consumer<EntityManager> funcToRun) {
		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Start a transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		funcToRun.accept(entityManager);

		transaction.commit();

		// if create-drop is used, the schema(tables) would be dropped by invoking this explicitly
		entityManager.close();
		entityManagerFactory.close();
	}


	private static void read() {
		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// returns null if employee does not exist
		Employee result = entityManager.find(Employee.class, 1L);

		System.out.println(" -> result: " + result);
		System.out.println(" -> dateOfBirth: " + result.getDateOfBirth());

		entityManager.close();
		entityManagerFactory.close();
	}


	private static void persistEmployees() {
		Employee employee = new Employee();
		employee.setName("Can");
		employee.setDateOfBirth(new Date());
		employee.setLocalDateTime(LocalDateTime.now());
		employee.setType(EmployeeType.FULL_TIME);

		Employee employee2 = new Employee();
		employee2.setName("Ahmet");
		employee2.setDateOfBirth(new Date());
		employee2.setLocalDateTime(LocalDateTime.now());
		employee2.setType(EmployeeType.PART_TIME);

		// creates an EntityManagerFactory using your persistence.xml config
		// we need to give the name of the persistence-unit we defined: "myApp"
		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Start a transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// saves both instances as rows in the table
		entityManager.persist(employee);
		entityManager.persist(employee2);

		transaction.commit();

		// if create-drop is used, the schema(tables) would be dropped by invoking this explicitly
		entityManager.close();
		entityManagerFactory.close();
	}

}

