package com.greydev.junitmockito;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class Main {

	public static void main(String[] args) {
		LambdaExceptionHandling.name();
		System.out.println();

		/*
		 * List<Person> personList = new ArrayList<>();
		 * Person p1 = new Person("ahmet", "b", 30);
		 * Person p2 = new Person("bilal", "c", 32);
		 * Person p3 = new Person("can", "a", 28);
		 * personList.add(p3);
		 * personList.add(p2);
		 * personList.add(p1);
		 * // 1. Sort list by last name
		 * Collections.sort(personList, (o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()));
		 * personList.sort((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()));
		 * // 2. Method that prints all elements of the list
		 * Consumer<List<Person>> printListBehaviour = (elems) -> {
		 * for (Person p : elems) {
		 * System.out.println(p);
		 * }
		 * };
		 * doStuffOnList(personList, printListBehaviour);
		 * printConditionally(personList, (p) -> true);
		 * // 3. Method that prints all people that have last name beginning with c
		 * Consumer<List<Person>> printLastNameCBehaviour = (elems) -> {
		 * for (Person p : elems) {
		 * if (p.getLastName().startsWith("c") ||
		 * p.getLastName().startsWith("C")) {
		 * System.out.println(p);
		 * }
		 * }
		 * };
		 * doStuffOnList(personList, printLastNameCBehaviour);
		 * printConditionally(personList, (p) -> p.getLastName().startsWith("c"));
		 */
	}


	public static <T> void doStuffOnList(List<T> elems, Consumer<List<T>> consumer) {
		consumer.accept(elems);
	}


	public static <T> void printConditionally(List<T> elems, Predicate<T> predicate) {
		for (T t : elems) {
			if (predicate.test(t)) {
				System.out.println(t);
			}
		}
	}
}
