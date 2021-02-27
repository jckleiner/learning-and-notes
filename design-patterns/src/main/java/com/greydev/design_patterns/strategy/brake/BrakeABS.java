package com.greydev.design_patterns.strategy.brake;

public class BrakeABS implements IBrakeStrategy {

	@Override
	public void brake() {
		System.out.println("Using ABS...");
	}

}
