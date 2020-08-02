package com.greydev.design_patterns.strategy.sort;

public class MyCollection {

	/*
	 * There are lots of different solutions to my problem. 
	 * I would like to choose one between those solutions easily ==> StrategyPattern
	 */
	private SortingStrategy sortingStrategy;

	public MyCollection() {
		this.sortingStrategy = new QuickSort(); // default sorting strategy
	}

	// You can also inject your own sorting algorithm if you wish
	public MyCollection(SortingStrategy sortingStrategy) {
		this.sortingStrategy = sortingStrategy;
	}

	public String sortString(String stringToSort) {
		return sortingStrategy.sort(stringToSort);
	}

}
