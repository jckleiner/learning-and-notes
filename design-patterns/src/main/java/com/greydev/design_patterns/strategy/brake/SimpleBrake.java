package com.greydev.design_patterns.strategy.brake;

public class SimpleBrake implements IBrakeStrategy {

	@Override
	public void brake() {
		System.out.println("Using the simple brake...");
	}

}
