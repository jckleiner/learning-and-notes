package com.example.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SlidingWindowTest {

	@Test
	void testFindMaxSubArray() {
		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { 4, 2, 1, 7, 8, 3, 2 }, 1)).isEqualTo(8);
		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { 4, 2, 1, 7, 8, 3, 2 }, 2)).isEqualTo(15);
		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { 4, 2, 1, 7, 8, 3, 2 }, 3)).isEqualTo(18);
		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { 4, 2, 1, 7, 8, 3, 2 }, 4)).isEqualTo(20);

		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { -2, 3, 2 }, 1)).isEqualTo(3);
		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { -2, 3, 2 }, 2)).isEqualTo(5);
		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { -2, 3, 2 }, 3)).isEqualTo(3);

		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { 5 }, 1)).isEqualTo(5);
		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { -5 }, 1)).isEqualTo(-5);
		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { -5, 2 }, 1)).isEqualTo(2);
		assertThat(SlidingWindow.findMaxSubArrayFixedSizeWindow(new int[] { -5, 2 }, 2)).isEqualTo(-3);
	}


	@Test
	void testSmallestSubarrayWithGivenSum() {
		assertThat(SlidingWindow.smallestSubarrayWithGivenSum(new int[] { 4, 2, 1, 7, 8, 3, 2 }, 8)).isEqualTo(1);
		assertThat(SlidingWindow.smallestSubarrayWithGivenSum(new int[] { 4, 2, 1, 7, 8, 3, 2 }, 18)).isEqualTo(3);
	}

	@Test
	void testLongestSubstringLengthWithKDistinctCharacters() {
		assertThat(SlidingWindow.longestSubstringLengthWithKDistinctCharacters("AAAHHIBC", 2)).isEqualTo(5);
	}
}

