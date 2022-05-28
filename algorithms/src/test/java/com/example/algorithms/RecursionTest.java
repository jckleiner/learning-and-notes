package com.example.algorithms;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RecursionTest {

	@Test
	void testSumOfElementsRecursive() {
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(1, 2, 3, 4, 5))).isEqualTo(15);
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(1))).isEqualTo(1);
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(-1))).isEqualTo(-1);
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(-1, -3))).isEqualTo(-4);
		assertThat(Recursion.sumOfElementsRecursive(Arrays.asList(-1, 3))).isEqualTo(2);
	}


	@Test
	void testNumOfElementsRecursive() {
		assertThat(Recursion.numOfElementsRecursive(Arrays.asList(1, 2, 3, 4, 5))).isEqualTo(5);
		assertThat(Recursion.numOfElementsRecursive(Arrays.asList(1, 2, 3, 4, 5 , 6, 6, 1))).isEqualTo(8);
		assertThat(Recursion.numOfElementsRecursive(Arrays.asList(-1, 3))).isEqualTo(2);
		assertThat(Recursion.numOfElementsRecursive(Arrays.asList(5))).isEqualTo(1);
		assertThat(Recursion.numOfElementsRecursive(Collections.emptyList())).isEqualTo(0);
	}


	@Test
	void testFindMaxElementRecursive() {
		assertThat(Recursion.findMaxElementRecursive(Arrays.asList(1, 2, 3, 4, 5), Integer.MIN_VALUE)).isEqualTo(5);
		assertThat(Recursion.findMaxElementRecursive(Arrays.asList(1, 100, 3, 4, 5 , 6, 6, 1), Integer.MIN_VALUE)).isEqualTo(100);
		assertThat(Recursion.findMaxElementRecursive(Arrays.asList(1, -100, 3, 4, 5 , -6, 6, 1), Integer.MIN_VALUE)).isEqualTo(6);
		assertThat(Recursion.findMaxElementRecursive(Arrays.asList(5), Integer.MIN_VALUE)).isEqualTo(5);
		assertThat(Recursion.findMaxElementRecursive(Collections.emptyList(), Integer.MIN_VALUE)).isEqualTo(0);
	}


	@Test
	void testFactorial() {
		assertThat(Recursion.factorial(0)).isEqualTo(1);
		assertThat(Recursion.factorial(1)).isEqualTo(1);
		assertThat(Recursion.factorial(2)).isEqualTo(2);
		assertThat(Recursion.factorial(3)).isEqualTo(6);
		assertThat(Recursion.factorial(4)).isEqualTo(24);
		assertThat(Recursion.factorial(5)).isEqualTo(120);
		assertThat(Recursion.factorial(6)).isEqualTo(720);
		assertThat(Recursion.factorial(7)).isEqualTo(5040);
	}


	@Test
	void testFibonacci() {
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
	}


	@Test
	void testEuclidsAlgorithm() {
	}
}