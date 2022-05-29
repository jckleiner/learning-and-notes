package com.example.algorithms;

import java.util.ArrayList;
import java.util.List;

import com.example.algorithms.util.Tuple;

import lombok.Builder;
import lombok.Data;


public class Knapsack_01 {

	// assuming the weights of the items are integers

	// TODO recursive

	// TODO other knapsack types

	public static Tuple<Integer, List<Item>> knapsack_01(List<Item> items, int capacity) {

		// we ignore the 0th row and 0th column, that's why + 1
		int[][] grid = new int[items.size() + 1][capacity + 1];

		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < capacity + 1; j++) {
				Item item = items.get(i - 1);

				int previousCellsValue = grid[i - 1][j];

				int current = 0;
				if (j >= item.weight) {
					current += item.value + grid[i - 1][j - item.weight];
				}

				int max = Math.max(previousCellsValue, current);

				grid[i][j] = max;
			}
		}

		printArray(grid, items);

		List<Item> itemsToPick = new ArrayList<>();
		int j = capacity;

		for (int i = grid.length - 1; i > 0; i--) {

			// if current cell is greater than the previous cell
			// it means the item was picked
			if (grid[i][j] != grid[i - 1][j]) {
				itemsToPick.add(items.get(i - 1));
				j -= items.get(i - 1).weight;
			}

		}

		Tuple<Integer, List<Item>> result = new Tuple<>();
		result.setFirst(grid[items.size()][capacity]);
		result.setSecond(itemsToPick);
		return result;
	}


	private static void printArray(int[][] grid, List<Item> items) {
		String result = "\n";

		for (int i = 0; i < grid.length; i++) {
			String row = "";

			for (int j = 0; j < grid[i].length; j++) {
				row = String.format("%s%2s", row, grid[i][j]);

				if (j + 1 < grid[i].length) {
					row = String.format("%s, ", row);
				}
			}

			String name = "Empty";
			if (i != 0) {
				name = items.get(i - 1).getName();
			}
			result += String.format("%20s: [%s]%n", name, row);
		}

		System.out.println(result);
	}


	@Data
	@Builder
	public static class Item {

		private int weight;
		private int value;
		private String name;
	}


}
