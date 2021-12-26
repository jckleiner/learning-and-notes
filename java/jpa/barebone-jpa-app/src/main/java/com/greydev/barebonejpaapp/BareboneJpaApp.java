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

		create();

		// update();

		// read();

		// delete();
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

		System.out.println(" ---> result: " + result);
		System.out.println(" ****** ");
		System.out.println(" ---> access card: " + result.getAccessCard());

		entityManager.close();
		entityManagerFactory.close();
	}


	private static void create() {

		Employee employee = new Employee();
		Employee employee2 = new Employee();
		AccessCard card1 = new AccessCard();
		AccessCard card2 = new AccessCard();

		card1.setIssueDate(new Date());
		card1.setActive(true);
		card1.setFirmwareVersion("1.0.0");
		card1.setEmployee(employee);

		card2.setIssueDate(new Date());
		card2.setActive(false);
		card2.setFirmwareVersion("1.0.1");
		card2.setEmployee(employee2);

		employee.setName("Can");
		employee.setDateOfBirth(new Date());
		employee.setLocalDateTime(LocalDateTime.now());
		employee.setType(EmployeeType.FULL_TIME);
		employee.setAccessCard(card1);

		employee2.setName("Ahmet");
		employee2.setDateOfBirth(new Date());
		employee2.setLocalDateTime(LocalDateTime.now());
		employee2.setType(EmployeeType.PART_TIME);
		employee2.setAccessCard(card2);

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
		entityManager.persist(card1);
		entityManager.persist(card2);

		transaction.commit();

		// if create-drop is used, the schema(tables) would be dropped by invoking this explicitly
		entityManager.close();
		entityManagerFactory.close();
	}


	private static void update() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Jonathan-Can");
		employee.setDateOfBirth(new Date());
		employee.setLocalDateTime(LocalDateTime.now());
		employee.setType(EmployeeType.PART_TIME);

		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Start a transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// since an entry with the id 1 already exist in the database
		// this persist operation will actually update that value
		entityManager.persist(employee);

		transaction.commit();

		entityManager.close();
		entityManagerFactory.close();
	}


	private static void delete() {
		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Start a transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Employee employee = entityManager.find(Employee.class, 1L);
		entityManager.remove(employee);

		transaction.commit();

		entityManager.close();
		entityManagerFactory.close();
	}

}

