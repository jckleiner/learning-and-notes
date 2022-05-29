package com.example.algorithms;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class QuickSortTest {

	@Test
	void quickSort() {

		assertThat(QuickSort.quickSortRecursive(Arrays.asList(3, 5, 2, 1, 4))).isEqualTo(Arrays.asList(1, 2, 3, 4, 5));
		assertThat(QuickSort.quickSortRecursive(Arrays.asList(1, 4, -12, 0, -33, 44, 55, 4, 1, 2, 3, 2)))
				.isEqualTo(Arrays.asList(-33, -12, 0, 1, 1, 2, 2, 3, 4, 4, 44, 55));
		assertThat(QuickSort.quickSortRecursive(Arrays.asList(1, 1))).isEqualTo(Arrays.asList(1, 1));
		assertThat(QuickSort.quickSortRecursive(Arrays.asList(1))).isEqualTo(Arrays.asList(1));
		assertThat(QuickSort.quickSortRecursive(Collections.emptyList()).isEmpty());
	}
}