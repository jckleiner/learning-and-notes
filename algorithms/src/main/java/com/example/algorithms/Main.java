package com.example.algorithms;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


public class Main {

	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>(Arrays.asList(2, 8, 5, 4, 1));
		SelectionSort.selectionSortInArray(nums);
		assertThat(nums).isEqualTo(Arrays.asList(1, 2, 4, 5, 8));

		// List<Integer> nums2 = new ArrayList<>(Arrays.asList(1, 2, 8, 5, 4));
		// TODO
		// nums2 = SelectionSort.selectionSortWithNewArray(nums2);
		// assertThat(nums2).isEqualTo(Arrays.asList(1, 2, 4, 5, 8));

		/*
		[2,7,11,15]
		9
		[3,2,4]
		6
		[3,3]
		6

		 */

		assertThat(twoSum(new int[] { 2, 7, 11, 15 }, 9)).isEqualTo(new int[] { 0, 1 });

	}


	public static int[] twoSum(int[] nums, int target) {

		Map<Integer, Integer> map = new HashMap<>();

		// [2,7,11,15]

		for (int i = 0; i > nums.length; i++) {

			int currentValue = nums[i];

			if (map.get(currentValue) != null) {
				int indexOfSecondValue = map.get(currentValue);
				return new int[] { i, indexOfSecondValue };
			}

			int rest = target - currentValue;

			map.put(rest, i);
			System.out.println("map: " + map);
		}

		return null;

	}

}
