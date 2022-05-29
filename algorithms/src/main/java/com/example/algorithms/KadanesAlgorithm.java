package com.example.algorithms;

import java.util.Arrays;

import com.example.algorithms.util.Tuple;


public class KadanesAlgorithm {

	/*
		Given an integer array nums, find the contiguous subarray
		(containing at least one number) which has the largest sum and return its sum.
		A subarray is a contiguous part of an array.

		According to Wikipedia Kadane's Algorithm requires at least one positive number
		but on leetcode the problem is asked inputting also all negative numbers
	 */
	public static int maximumSubarrayKadane(int[] nums) {
		int current = nums[0];
		int max = nums[0];

		for (int i = 1; i < nums.length; i++) {

			current = Math.max(nums[i], nums[i] + current);

			if (current > max) {
				max = current;
			}
		}

		return max;
	}


	public static Tuple<Integer, Integer[]> maximumSubarrayKadaneWithIndices(int[] nums) {
		int current = nums[0];
		int max = nums[0];

		int currentBegin = 0;
		int currentEnd = 0;
		int maxBegin = 0;
		int maxEnd = 0;

		for (int i = 1; i < nums.length; i++) {

			// determine where current indices should point to
			if (nums[i] > nums[i] + current) {
				currentBegin = i;
				currentEnd = i;
			} else {
				currentEnd++;
			}

			current = Math.max(nums[i], nums[i] + current);

			// if we found a new max, set the max values and index pointers
			if (current > max) {
				maxBegin = currentBegin;
				maxEnd = currentEnd;
				max = current;
			}
		}

		Tuple<Integer, Integer[]> result = new Tuple<>();
		result.setFirst(max);
		result.setSecond(Arrays.stream(Arrays.copyOfRange(nums, maxBegin, maxEnd + 1)).boxed().toArray(Integer[]::new));
		return result;
	}

}
