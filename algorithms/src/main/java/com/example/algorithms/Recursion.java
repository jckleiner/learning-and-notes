package com.example.algorithms;

import java.util.*;


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
		// this example assumes fib(1) == 1, the first element in the sequence is 1
		if (num == 0) {
			return 0;
		}

		if (num == 1) {
			return 1;
		}

		// recursive case
		return fibonacci(num - 1) + fibonacci(num - 2);
	}


	public static Long fibonacciEfficient(int num, HashMap<Long, Long> memo) {
		if (memo == null) {
			memo = new HashMap<>();
		}
		// base case
		if (num == 0) {
			return 0L;
		}
		if (num == 1) {
			return 1L;
		}

		// was this method with this argument already calculated?
		if (memo.get(new Long(num)) != null) {
			return memo.get(new Long(num));
		}

		long result = fibonacciEfficient(num - 1, memo) + fibonacciEfficient(num - 2, memo);

		memo.put((long) num, result);

		return result;
	}


	public static int gridTraveler(int m, int n) {
		// base case
		if (n == 0 || m == 0) {
			return 0;
		}
		// we could also do (n == 1 || m == 1) here, if we are concerned only about
		// the number of ways to get to the end. But doing (n == 1 && m == 1) would be more helpful
		// if the problem wants the exact steps taken to get to the end
		if (n == 1 && m == 1) {
			return 1; // we landed on the end cell
		}

		// recursive case
		return gridTraveler(m - 1, n) + gridTraveler(m, n - 1);
	}


	public static boolean canSum(int targetSum, Integer[] arr) {
		// you can reach 0 as the targetSum also when you take no elements from the array
		if (targetSum == 0) {
			return true;
		}

		boolean bool = false;

		for (Integer i : arr) {
			if (targetSum >= i && i != 0) {
				bool = bool || canSum(targetSum - i, arr);
			}
		}

		return bool;
	}


	public static boolean canSumMemoized(int targetSum, Integer[] arr, Map<Integer, Boolean> memo) {
		if (memo == null) {
			memo = new HashMap<>();
		}
		// you can reach 0 as the targetSum also when you take no elements from the array
		if (targetSum == 0) {
			return true;
		}

		if (memo.get(targetSum) != null) {
			return memo.get(targetSum);
		}

		boolean bool = false;

		for (Integer i : arr) {
			if (targetSum < i || i == 0) {
				continue;
			}
			if (canSumMemoized(targetSum - i, arr, memo)) {
				memo.put(targetSum, true);
				return true;
			}

		}

		memo.put(targetSum, bool);

		return bool;
	}


	public static List<Integer> howSum(int targetSum, Integer[] numbers, List<Integer> numbersForFirstPossibleSum) {
		if (numbersForFirstPossibleSum == null) {
			numbersForFirstPossibleSum = new ArrayList<>();
		}

		if (targetSum == 0) {
			return numbersForFirstPossibleSum; // indicates true
		}

		for (Integer i : numbers) {
			if (targetSum < i || i == 0) {
				continue;
			}

			List<Integer> result = howSum(targetSum - i, numbers, numbersForFirstPossibleSum);

			// if result is not null, it means that this target sum can be reached by the numbers in the given array
			if (result != null) {
				numbersForFirstPossibleSum.add(i);
				return numbersForFirstPossibleSum; // indicates true
			}
		}

		return null; // indicates false
	}


	public static List<Integer> howSumMemoized(int targetSum, Integer[] numbers,
			List<Integer> numbersForFirstPossibleSum,
			Map<Integer, List<Integer>> memo) {

		if (numbersForFirstPossibleSum == null) {
			numbersForFirstPossibleSum = new ArrayList<>();
		}

		if (memo == null) {
			memo = new HashMap<>();
		}

		if (memo.get(targetSum) != null) {
			return memo.get(targetSum);
		}

		if (targetSum == 0) {
			memo.put(0, numbersForFirstPossibleSum);
			return numbersForFirstPossibleSum; // indicates true
		}

		for (Integer i : numbers) {
			if (targetSum < i || i == 0) {
				continue;
			}

			List<Integer> result = howSumMemoized(targetSum - i, numbers, numbersForFirstPossibleSum, memo);

			// if result is not null, it means that this target sum can be reached by the numbers in the given array
			if (result != null) {
				numbersForFirstPossibleSum.add(i);
				memo.put(targetSum, numbersForFirstPossibleSum);
				return numbersForFirstPossibleSum; // indicates true
			}
		}

		memo.put(targetSum, null);
		return null; // indicates false
	}


	public static Long euclidsAlgorithm(List<Integer> list) {

		return 0L;
	}
}
