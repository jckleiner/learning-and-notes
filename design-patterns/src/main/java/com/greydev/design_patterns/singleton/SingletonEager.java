package com.greydev.design_patterns.singleton;

public class SingletonEager {

	private static SingletonEager instance = new SingletonEager();

	private SingletonEager() {
	}

	public static SingletonEager getInstance() {
		return instance;
	}

	// other methods

}
