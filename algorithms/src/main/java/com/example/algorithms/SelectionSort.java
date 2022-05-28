package com.example.algorithms;

import java.util.ArrayList;
import java.util.List;


public class SelectionSort {

	public static void selectionSortInArray(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			int currentMinIndex = i;

			// find the index of the smallest element
			for (int j = i; j < list.size(); j++) {
				if (list.get(j) < list.get(currentMinIndex)) {
					currentMinIndex = j;
				}
			}

			// swap the min element with the current index
			int temp = list.get(i);
			list.set(i, list.get(currentMinIndex));
			list.set(currentMinIndex, temp);
		}
	}


	public static List<Integer> selectionSortWithNewArray(List<Integer> list) {

		List<Integer> newList = new ArrayList<>();

		//  1, 2, 8, 5, 4

		// TODO use iterator

		for (int i = 0; i < list.size(); i++) {
			System.out.println("i: " + i);
			System.out.println("current elem: " + list.get(i));
			int currentMinIndex = i;

			// find the index of the smallest element
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j) < list.get(currentMinIndex)) {
					currentMinIndex = j;
				}
			}

			System.out.println(String.format("currentMinIndex: %s, element at currentMinIndex: %s", currentMinIndex, list.get(currentMinIndex)));

			// add the smallest number to the new list
			newList.add(list.get(currentMinIndex));
			System.out.println(String.format("newList: %s", newList));

			// remove the smallest number from the original list
			list.remove(currentMinIndex);
			System.out.println(String.format("oldList after removal: %s%n", list));
		}

		return newList;
	}
}
