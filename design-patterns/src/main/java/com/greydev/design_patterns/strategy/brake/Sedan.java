package com.greydev.design_patterns.strategy.brake;

public class Sedan extends Car {

	// instead of implementing a new behaviour for each child class
	// we can inject the wanted behaviour easily
	public Sedan() {
		this.brakeStrategy = new SimpleBrake();
	}

}