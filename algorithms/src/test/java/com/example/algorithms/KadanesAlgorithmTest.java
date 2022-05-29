package com.example.algorithms;

import org.junit.jupiter.api.Test;

import com.example.algorithms.util.Tuple;

import static org.assertj.core.api.Assertions.assertThat;


class KadanesAlgorithmTest {

	@Test
	void testMaximumSubarrayKadane() {
		assertThat(KadanesAlgorithm.maximumSubarrayKadane(new int[] { -2, 3, 2, -1 })).isEqualTo(5);
		assertThat(KadanesAlgorithm.maximumSubarrayKadane(new int[] { -1, -2, -3, -4 })).isEqualTo(-1);
		assertThat(KadanesAlgorithm.maximumSubarrayKadane(new int[] { 1, 2, 3, -2, 5 })).isEqualTo(9);
		assertThat(KadanesAlgorithm.maximumSubarrayKadane(new int[] { 8 })).isEqualTo(8);
		assertThat(KadanesAlgorithm.maximumSubarrayKadane(new int[] { -3, -2, -2, -3 })).isEqualTo(-2);
		assertThat(KadanesAlgorithm.maximumSubarrayKadane(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }))
				.isEqualTo(6);
	}


	@Test
	void testMaximumSubarrayKadaneWithIndices() {
		Tuple<Integer, Integer[]> result = KadanesAlgorithm.maximumSubarrayKadaneWithIndices(new int[] { -2, 3, 2, -1 });
		assertThat(result.getFirst()).isEqualTo(5);
		assertThat(result.getSecond()).isEqualTo(new Integer[] { 3, 2 });

		Tuple<Integer, Integer[]> result2 = KadanesAlgorithm.maximumSubarrayKadaneWithIndices(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 });
		assertThat(result2.getFirst()).isEqualTo(6);
		assertThat(result2.getSecond()).isEqualTo(new Integer[] { 4, -1, 2, 1 });
	}


	@Test
	void testMaximumSubarrayKadaneWithIndices_allNegativeNumbers() {
		Tuple<Integer, Integer[]> result = KadanesAlgorithm.maximumSubarrayKadaneWithIndices(new int[] { -3, -2, -2, -3 });
		assertThat(result.getFirst()).isEqualTo(-2);
		assertThat(result.getSecond()).isEqualTo(new Integer[] { -2 });
	}


	@Test
	void testMaximumSubarrayKadaneWithIndices_oneElement() {
		Tuple<Integer, Integer[]> result = KadanesAlgorithm.maximumSubarrayKadaneWithIndices(new int[] { 8 });
		assertThat(result.getFirst()).isEqualTo(8);
		assertThat(result.getSecond()).isEqualTo(new Integer[] { 8 });
	}
}

