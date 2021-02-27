package com.greydev.design_patterns.strategy.brake;

public class SUV extends Car {

	// instead of implementing a new behaviour for each child class
	// we can inject the wanted behaviour easily
	public SUV() {
		this.brakeStrategy = new BrakeABS();
	}

}
