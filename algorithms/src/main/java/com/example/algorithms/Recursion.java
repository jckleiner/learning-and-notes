package com.example.algorithms;

import java.util.List;


public class Recursion {

	// Keep in mind that these methods are not "production ready"
	// A better way to write these would be to use Optional return values
	// but that would make the code more verbose and harder to understand the concepts

	// TODO euclids algorithm

	// TODO tail recursion?


	public static Integer sumOfElementsRecursive(List<Integer> list) {
		// base case
		if (list.size() == 1) {
			return list.get(0);
		}

		// recursive case
		return list.get(0) + sumOfElementsRecursive(list.subList(1, list.size()));
	}


	public static Integer numOfElementsRecursive(List<Integer> list) {
		// base case
		if (list.isEmpty()) {
			return 0;
		}

		// base case
		if (list.size() == 1) {
			return 1;
		}

		// recursive case
		return 1 + numOfElementsRecursive(list.subList(1, list.size()));
	}


	public static Integer findMaxElementRecursive(List<Integer> list, int maxSoFar) {

		// base case
		if (list.isEmpty()) {
			return 0;
		}

		// base case
		if (list.size() == 1) {
			if (maxSoFar > list.get(0)) {
				return maxSoFar;
			}
			return list.get(0);
		}

		if (maxSoFar < list.get(0)) {
			maxSoFar = list.get(0);
		}

		// recursive case
		return findMaxElementRecursive(list.subList(1, list.size()), maxSoFar);
	}


	// factorial of negative numbers is undefined.
	public static Long factorial(int num) {

		// base case
		if (num == 0) {
			return 1L;
		}

		// recursive case
		return num * factorial(num - 1);
	}


	// first number in the fibonacci sequence can be 0 or 1
	// see the definition in the given question or ask how they define it
	public static Integer fibonacci(int num) {

		// base case
		if (num == 0) {
			return 0;
		}

		// base case
		// this example assumes fib(1) == 1, the first element in the sequence is 1
		if (num == 1) {
			return 1;
		}

		// recursive case
		return fibonacci(num - 1) + fibonacci(num - 2);
	}


	public static Long euclidsAlgorithm(List<Integer> list) {

		return 0L;
	}
}
