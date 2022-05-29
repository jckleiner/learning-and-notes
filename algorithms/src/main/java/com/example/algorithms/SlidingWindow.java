package com.example.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;


public class SlidingWindow {

	// TODO negative numbers?


	// fixed size window
	public static int findMaxSubArrayFixedSizeWindow(int[] arr, int windowLength) {
		int runningSum;
		int maxSum = Integer.MIN_VALUE;
		int endIndex;

		for (int startIndex = 0; startIndex + windowLength - 1 < arr.length; startIndex++) {
			// the window is has a fixed size of windowLength
			endIndex = startIndex + windowLength - 1;

			runningSum = IntStream
					.range(startIndex, endIndex + 1)
					.map(n -> arr[n])
					.sum();

			maxSum = Math.max(maxSum, runningSum);
		}

		return maxSum;
	}

	// TODO Find the number of subarrays whose sum is >= n
	// is this even possible with DP?


	/*
		Find the length of the smallest subarray whose sum is >= n
	 */
	public static int smallestSubarrayWithGivenSum(int[] arr, int n) {

		int result = 0;
		int start = 0;
		int end = 0;

		for (int i = 0; end < arr.length; i++) {

			int sum = Arrays.stream(Arrays.copyOfRange(arr, start, end + 1)).sum();

			if (sum >= n) {
				result = end - start + 1;

				// you could return here but for other problems this logic is probably needed...
				if (start == end) {
					start++;
					end++;
				} else if (start < end) {
					start++;
				}

			} else {
				end++;
			}

		}

		return result;
	}


	public static int longestSubstringLengthWithKDistinctCharacters(String str, int k) {

		System.out.println(str);

		char[] arr = str.toCharArray();

		Map<Character, Integer> map = new HashMap<>();

		int start = 0;
		int end = 0;
		int maxSoFar = Integer.MIN_VALUE;

		while (end < arr.length) {

			if (map.keySet().size() == k) {
				int charCount = map.values().stream().mapToInt(Integer::valueOf).sum();
				maxSoFar = Math.max(maxSoFar, charCount);
			}

			// if the condition is violated, shrink the window from the left side
			if (map.keySet().size() > k) {

				Integer countOfChar = map.get(arr[start]);

				if (countOfChar == 1) {
					map.remove(arr[start]);
				} else if (countOfChar != null) {
					map.put(arr[start], map.get(arr[start]) - 1);
				}
				start++;
			} else {
				int count = Optional.ofNullable(map.get(arr[end])).orElse(0);
				map.put(arr[end], count + 1);
				end++;
			}

			System.out.println(map);

		}

		return maxSoFar;
	}
}
