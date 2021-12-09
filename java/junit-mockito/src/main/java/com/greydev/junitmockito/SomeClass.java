package com.greydev.junitmockito;

import java.util.Arrays;


public class SomeClass {

	public SomeClass() {
		System.out.println("*** SomeClass instance created ***");
	}


	public static void main(String[] args) {
		SomeClass someObj = new SomeClass();

//		Thread t = new Thread(() -> staticPrintHello());
//		Thread t2 = new Thread(() -> new SomeClass().instancePrintHello());

		// ContainingClass::staticMethodName
		Thread t3 = new Thread(SomeClass::staticPrintHello);

		// Reference to an instance method of a particular object
		Thread t4 = new Thread(someObj::instancePrintHello);

		// Reference to an instance method of an arbitrary object of a particular type
		someObj.test((s1, s2, s3) -> System.out.println("test 1"));
		someObj.test(SomeClass::twoArgs);

		// Reference to a constructor
		Thread t5 = new Thread(SomeClass::new);

		t3.start();
		t4.start();
		t5.start();

		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
		Arrays.sort(stringArray, (a, b) -> 1);
		Arrays.sort(stringArray, (String a, String b) -> a.compareToIgnoreCase(b));
		Arrays.sort(stringArray, String::compareToIgnoreCase);

//		Comparator<T>   int compare(T o1, T o2);

		// String::compareToIgnoreCase
		// (String a, String b) -> a.compareToIgnoreCase(b)

	}


	public void test(Test obj) {
		System.out.println("***************");
		obj.bla(null, null, null);
	}


	public void oneArg(SomeClass s1) {
		System.out.println("oneArg");
	}


	public void twoArgs(SomeClass s1, SomeClass s2) {
		System.out.println("twoArgs");
	}


	public void twoArgsString(String s1, String s2) {
		System.out.println("twoArgs String");
	}


	public static void staticPrintHello() {
		System.out.println("static hello ");
	}


	public void instancePrintHello() {
		System.out.println("instance hello ");
	}

//	public static void staticPrintNumber(int i) {
//		System.out.println("static: " + i);
//	}
//
//
//	public void instancePrintNumber(int i) {
//		System.out.println("instance: " + i);
}

interface Test {

	void bla(SomeClass s1, SomeClass s2, SomeClass s3);

}
