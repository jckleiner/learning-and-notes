package com.example.algorithms;

import java.util.List;
import java.util.Optional;


public class BinarySearch {

	public static Optional<Integer> binarySearch(List<Integer> list, int searchedElement) {

		int min = 0;
		int max = list.size() - 1;

		while (min <= max) {
			// will be rounded down
			int mid = min + max;

			if (list.get(mid) == searchedElement) {
				return Optional.of(mid);
			}

			if (list.get(mid) > searchedElement) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}

		return Optional.empty();
	}


	// max is inclusive
	public static Optional<Integer> binarySearchRecursive(List<Integer> list, int searchedElement, int min, int max) {
		// base case
		if (min > max) {
			return Optional.empty();
		}

		int mid = (min + max) / 2;

		// base case
		if (list.get(mid) == searchedElement) {
			return Optional.of(mid);
		}

		if (list.get(mid) > searchedElement) {
			max = mid - 1;
		} else {
			min = mid + 1;
		}

		// recursive case
		return binarySearchRecursive(list, searchedElement, min, max);

	}
}
