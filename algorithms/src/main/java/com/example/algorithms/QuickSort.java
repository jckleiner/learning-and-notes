package com.example.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class QuickSort {

	// creates new arrays, it is NOT in place
	public static List<Integer> quickSortRecursive(List<Integer> list) {

		// base case
		if (list.size() < 2) {
			return list;
		}

		int pivot = list.get(0);

		List<Integer> less = new ArrayList<>();
		List<Integer> greater = new ArrayList<>();

		for (Integer i : list.subList(1, list.size())) {
			if (i <= pivot) {
				less.add(i);
			} else {
				greater.add(i);
			}
		}

		List<Integer> newList = new ArrayList<>(less.size() + greater.size() + 1);
		newList.addAll(quickSortRecursive(less));
		newList.addAll(Arrays.asList(pivot));
		newList.addAll(quickSortRecursive(greater));

		// quickSort(less) + [pivot] + quickSort(greater)
		return newList;
	}
}
