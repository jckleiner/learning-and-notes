package com.example.algorithms;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RecursionTest {

	@Test void testSumOfElementsRecursive() {
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(1, 2, 3, 4, 5))).isEqualTo(15);
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(1))).isEqualTo(1);
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(-1))).isEqualTo(-1);
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(-1, -3))).isEqualTo(-4);
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(-1, 3))).isEqualTo(2);
	}


	@Test void testNumOfElementsRecursive() {
		assertThat(Recursion.numOfElementsRecursive(Arrays.asList(1, 2, 3, 4, 5))).isEqualTo(5);
		assertThat(Recursion.numOfElementsRecursive(Arrays.asList(1, 2, 3, 4, 5, 6, 6, 1))).isEqualTo(8);
		assertThat(Recursion.numOfElementsRecursive(Arrays.asList(-1, 3))).isEqualTo(2);
		assertThat(Recursion.numOfElementsRecursive(Arrays.asList(5))).isEqualTo(1);
		assertThat(Recursion.numOfElementsRecursive(Collections.emptyList())).isEqualTo(0);
	}


	@Test void testFindMaxElementRecursive() {
		assertThat(Recursion.findMaxElementRecursive(Arrays.asList(1, 2, 3, 4, 5), Integer.MIN_VALUE)).isEqualTo(5);
		assertThat(Recursion.findMaxElementRecursive(Arrays.asList(1, 100, 3, 4, 5, 6, 6, 1), Integer.MIN_VALUE)).isEqualTo(100);
		assertThat(Recursion.findMaxElementRecursive(Arrays.asList(1, -100, 3, 4, 5, -6, 6, 1), Integer.MIN_VALUE)).isEqualTo(6);
		assertThat(Recursion.findMaxElementRecursive(Arrays.asList(5), Integer.MIN_VALUE)).isEqualTo(5);
		assertThat(Recursion.findMaxElementRecursive(Collections.emptyList(), Integer.MIN_VALUE)).isEqualTo(0);
	}


	@Test void testFactorial() {
		assertThat(Recursion.factorial(0)).isEqualTo(1);
		assertThat(Recursion.factorial(1)).isEqualTo(1);
		assertThat(Recursion.factorial(2)).isEqualTo(2);
		assertThat(Recursion.factorial(3)).isEqualTo(6);
		assertThat(Recursion.factorial(4)).isEqualTo(24);
		assertThat(Recursion.factorial(5)).isEqualTo(120);
		assertThat(Recursion.factorial(6)).isEqualTo(720);
		assertThat(Recursion.factorial(7)).isEqualTo(5040);
	}


	@Test void testFibonacci() {
		assertThat(Recursion.fibonacci(0)).isEqualTo(0);
		assertThat(Recursion.fibonacci(1)).isEqualTo(1);
		assertThat(Recursion.fibonacci(2)).isEqualTo(1);
		assertThat(Recursion.fibonacci(3)).isEqualTo(2);
		assertThat(Recursion.fibonacci(4)).isEqualTo(3);
		assertThat(Recursion.fibonacci(5)).isEqualTo(5);
		assertThat(Recursion.fibonacci(6)).isEqualTo(8);
		assertThat(Recursion.fibonacci(7)).isEqualTo(13);
		assertThat(Recursion.fibonacci(8)).isEqualTo(21);
		assertThat(Recursion.fibonacci(9)).isEqualTo(34);
		assertThat(Recursion.fibonacci(10)).isEqualTo(55);
		// runs for a loong time
		// assertThat(Recursion.fibonacciRecursive(50)).isEqualTo(55);
	}


	@Test void testFibonacciEfficient() {
		assertThat(Recursion.fibonacciEfficient(0, null)).isEqualTo(0);
		assertThat(Recursion.fibonacciEfficient(1, null)).isEqualTo(1);
		assertThat(Recursion.fibonacciEfficient(2, null)).isEqualTo(1);
		assertThat(Recursion.fibonacciEfficient(3, null)).isEqualTo(2);
		assertThat(Recursion.fibonacciEfficient(4, null)).isEqualTo(3);
		assertThat(Recursion.fibonacciEfficient(5, null)).isEqualTo(5);
		assertThat(Recursion.fibonacciEfficient(6, null)).isEqualTo(8);
		assertThat(Recursion.fibonacciEfficient(7, null)).isEqualTo(13);
		assertThat(Recursion.fibonacciEfficient(8, null)).isEqualTo(21);
		assertThat(Recursion.fibonacciEfficient(9, null)).isEqualTo(34);
		assertThat(Recursion.fibonacciEfficient(10, null)).isEqualTo(55);
		assertThat(Recursion.fibonacciEfficient(30, null)).isEqualTo(832040);
		assertThat(Recursion.fibonacciEfficient(40, null)).isEqualTo(102334155);
		assertThat(Recursion.fibonacciEfficient(50, null)).isEqualTo(12586269025L);
	}


	@Test void testGridTraveler() {
		assertThat(Recursion.gridTraveler(0, 1)).isEqualTo(0);
		assertThat(Recursion.gridTraveler(0, 7)).isEqualTo(0);
		assertThat(Recursion.gridTraveler(7, 0)).isEqualTo(0);
		assertThat(Recursion.gridTraveler(1, 1)).isEqualTo(1);
		assertThat(Recursion.gridTraveler(1, 7)).isEqualTo(1);
		assertThat(Recursion.gridTraveler(2, 1)).isEqualTo(1);
		assertThat(Recursion.gridTraveler(1, 2)).isEqualTo(1);
		assertThat(Recursion.gridTraveler(2, 2)).isEqualTo(2);
		assertThat(Recursion.gridTraveler(3, 3)).isEqualTo(6);
		assertThat(Recursion.gridTraveler(4, 3)).isEqualTo(10);
		assertThat(Recursion.gridTraveler(4, 4)).isEqualTo(20);
	}


	@Test void testCanSum() {
		assertThat(Recursion.canSum(7, new Integer[] { 0 })).isFalse();
		assertThat(Recursion.canSum(0, new Integer[] { 7, 2, 3 })).isTrue();
		assertThat(Recursion.canSum(7, new Integer[] { 5, 3, 4, 7 })).isTrue();
		assertThat(Recursion.canSum(7, new Integer[] { 5, 3, 4 })).isTrue();
		assertThat(Recursion.canSum(7, new Integer[] { 5, 3 })).isFalse();
		assertThat(Recursion.canSum(8, new Integer[] { 2, 3 })).isTrue();
		assertThat(Recursion.canSum(11, new Integer[] { 2, 3 })).isTrue();
		assertThat(Recursion.canSum(27, new Integer[] { 8, 1 })).isTrue();
		// memoized
		assertThat(Recursion.canSumMemoized(300, new Integer[] { 7, 14 }, null)).isFalse();
	}


	@Test void testHowSum() {
		assertThat(Recursion.howSum(7, new Integer[] { 5, 3, 4, 7 }, null)).containsAll(Arrays.asList(3, 4));
		assertThat(Recursion.howSum(5, new Integer[] { 8, 1 },null)).containsAll(Arrays.asList(1, 1, 1, 1, 1));
		assertThat(Recursion.howSum(7, new Integer[] { 2, 4 }, null)).isNull();
		assertThat(Recursion.howSum(0, new Integer[] { 2, 4, 1, 5 }, null)).isEqualTo(Collections.emptyList());
		assertThat(Recursion.howSum(8, new Integer[] { 2, 3 }, null)).containsAll(Arrays.asList(2, 2, 2, 2));

		assertThat(Recursion.howSumMemoized(300, new Integer[] { 7, 14 }, null, null)).isNull();
	}


	@Test void testEuclidsAlgorithm() {
	}
}