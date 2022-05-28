package com.example.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class BinarySearchTest {

	@Test
	void testBinarySearch_positiveIntegers() {
		List<Integer> integers = new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9, 10));

		assertThat(BinarySearch.binarySearch(integers, 5).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearch(integers, 6).get()).isEqualTo(1);
		assertThat(BinarySearch.binarySearch(integers, 7).get()).isEqualTo(2);
		assertThat(BinarySearch.binarySearch(integers, 8).get()).isEqualTo(3);
		assertThat(BinarySearch.binarySearch(integers, 9).get()).isEqualTo(4);
		assertThat(BinarySearch.binarySearch(integers, 10).get()).isEqualTo(5);
	}


	@Test
	void testBinarySearch_positiveAndNegativeIntegers() {
		List<Integer> integers2 = new ArrayList<>(Arrays.asList(-100, -50, -25, 0, 1, 3, 5, 88, 111));

		assertThat(BinarySearch.binarySearch(integers2, -100).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearch(integers2, -50).get()).isEqualTo(1);
		assertThat(BinarySearch.binarySearch(integers2, -25).get()).isEqualTo(2);
		assertThat(BinarySearch.binarySearch(integers2, 0).get()).isEqualTo(3);
		assertThat(BinarySearch.binarySearch(integers2, 1).get()).isEqualTo(4);
		assertThat(BinarySearch.binarySearch(integers2, 3).get()).isEqualTo(5);
		assertThat(BinarySearch.binarySearch(integers2, 5).get()).isEqualTo(6);
		assertThat(BinarySearch.binarySearch(integers2, 88).get()).isEqualTo(7);
		assertThat(BinarySearch.binarySearch(integers2, 111).get()).isEqualTo(8);
		assertThat(BinarySearch.binarySearch(integers2, -500)).isEmpty();
		assertThat(BinarySearch.binarySearch(integers2, 200)).isEmpty();

		List<Integer> integers3 = new ArrayList<>(Arrays.asList(5));

		assertThat(BinarySearch.binarySearch(integers3, 5).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearch(integers3, 1)).isEmpty();
		assertThat(BinarySearch.binarySearch(integers3, 7)).isEmpty();
	}


	@Test
	void testBinarySearch_negativeIntegers() {
		List<Integer> integers = new ArrayList<>(Arrays.asList(-100, -90, -80, -50, -30, -20, -15));

		assertThat(BinarySearch.binarySearch(integers, -100).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearch(integers, -90).get()).isEqualTo(1);
		assertThat(BinarySearch.binarySearch(integers, -80).get()).isEqualTo(2);
		assertThat(BinarySearch.binarySearch(integers, -50).get()).isEqualTo(3);
		assertThat(BinarySearch.binarySearch(integers, -30).get()).isEqualTo(4);
		assertThat(BinarySearch.binarySearch(integers, -20).get()).isEqualTo(5);
		assertThat(BinarySearch.binarySearch(integers, -15).get()).isEqualTo(6);

		assertThat(BinarySearch.binarySearch(integers, -500)).isEmpty();
		assertThat(BinarySearch.binarySearch(integers, 200)).isEmpty();
	}


	@Test
	void testBinarySearch_singleElement() {
		List<Integer> integers3 = new ArrayList<>(Arrays.asList(5));

		assertThat(BinarySearch.binarySearch(integers3, 5).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearch(integers3, 1)).isEmpty();
		assertThat(BinarySearch.binarySearch(integers3, 7)).isEmpty();
	}


	@Test
	void testBinarySearchRecursive_positiveIntegers() {
		List<Integer> integers = new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9, 10));

		assertThat(BinarySearch.binarySearchRecursive(integers, 5, 0, integers.size() - 1).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearchRecursive(integers, 6, 0, integers.size() - 1).get()).isEqualTo(1);
		assertThat(BinarySearch.binarySearchRecursive(integers, 7, 0, integers.size() - 1).get()).isEqualTo(2);
		assertThat(BinarySearch.binarySearchRecursive(integers, 8, 0, integers.size() - 1).get()).isEqualTo(3);
		assertThat(BinarySearch.binarySearchRecursive(integers, 9, 0, integers.size() - 1).get()).isEqualTo(4);
		assertThat(BinarySearch.binarySearchRecursive(integers, 10, 0, integers.size() - 1).get()).isEqualTo(5);
		assertThat(BinarySearch.binarySearchRecursive(integers, 4, 0, integers.size() - 1)).isEmpty();
		assertThat(BinarySearch.binarySearchRecursive(integers, 11, 0, integers.size() - 1)).isEmpty();
	}


	@Test
	void testBinarySearchRecursive_positiveAndNegativeIntegers() {
		List<Integer> integers = new ArrayList<>(Arrays.asList(-100, -50, -25, 0, 1, 3, 5, 88, 111));

		assertThat(BinarySearch.binarySearchRecursive(integers, -100, 0, integers.size() - 1).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearchRecursive(integers, -50, 0, integers.size() - 1).get()).isEqualTo(1);
		assertThat(BinarySearch.binarySearchRecursive(integers, -25, 0, integers.size() - 1).get()).isEqualTo(2);
		assertThat(BinarySearch.binarySearchRecursive(integers, 0, 0, integers.size() - 1).get()).isEqualTo(3);
		assertThat(BinarySearch.binarySearchRecursive(integers, 1, 0, integers.size() - 1).get()).isEqualTo(4);
		assertThat(BinarySearch.binarySearchRecursive(integers, 3, 0, integers.size() - 1).get()).isEqualTo(5);
		assertThat(BinarySearch.binarySearchRecursive(integers, 5, 0, integers.size() - 1).get()).isEqualTo(6);
		assertThat(BinarySearch.binarySearchRecursive(integers, 88, 0, integers.size() - 1).get()).isEqualTo(7);
		assertThat(BinarySearch.binarySearchRecursive(integers, 111, 0, integers.size() - 1).get()).isEqualTo(8);
		assertThat(BinarySearch.binarySearchRecursive(integers, -500, 0, integers.size() - 1)).isEmpty();
		assertThat(BinarySearch.binarySearchRecursive(integers, 200, 0, integers.size() - 1)).isEmpty();
	}


	@Test
	void testBinarySearchRecursive_negativeIntegers() {
		List<Integer> integers = new ArrayList<>(Arrays.asList(-100, -90, -80, -50, -30, -20, -15));

		assertThat(BinarySearch.binarySearchRecursive(integers, -100, 0, integers.size() - 1).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearchRecursive(integers, -90, 0, integers.size() - 1).get()).isEqualTo(1);
		assertThat(BinarySearch.binarySearchRecursive(integers, -80, 0, integers.size() - 1).get()).isEqualTo(2);
		assertThat(BinarySearch.binarySearchRecursive(integers, -50, 0, integers.size() - 1).get()).isEqualTo(3);
		assertThat(BinarySearch.binarySearchRecursive(integers, -30, 0, integers.size() - 1).get()).isEqualTo(4);
		assertThat(BinarySearch.binarySearchRecursive(integers, -20, 0, integers.size() - 1).get()).isEqualTo(5);
		assertThat(BinarySearch.binarySearchRecursive(integers, -15, 0, integers.size() - 1).get()).isEqualTo(6);

		assertThat(BinarySearch.binarySearchRecursive(integers, -500, 0, integers.size() - 1)).isEmpty();
		assertThat(BinarySearch.binarySearchRecursive(integers, 200, 0, integers.size() - 1)).isEmpty();
	}


	@Test
	void testBinarySearchRecursive_singleElement() {
		List<Integer> integers = new ArrayList<>(Arrays.asList(5));

		assertThat(BinarySearch.binarySearchRecursive(integers, 5, 0, integers.size() - 1).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearchRecursive(integers, 1, 0, integers.size() - 1)).isEmpty();
		assertThat(BinarySearch.binarySearchRecursive(integers, 7, 0, integers.size() - 1)).isEmpty();

		List<Integer> integers2 = new ArrayList<>(Arrays.asList(-5));

		assertThat(BinarySearch.binarySearchRecursive(integers2, -5, 0, integers2.size() - 1).get()).isEqualTo(0);
		assertThat(BinarySearch.binarySearchRecursive(integers2, -7, 0, integers2.size() - 1)).isEmpty();
		assertThat(BinarySearch.binarySearchRecursive(integers2, 1, 0, integers2.size() - 1)).isEmpty();
	}
}