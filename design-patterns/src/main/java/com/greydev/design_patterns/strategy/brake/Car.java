package com.greydev.design_patterns.strategy.brake;

/* instead of making every child class implement its behavior each time
 	 decouple the algorithm to give the new child classes the ability to choose 
	 and implement a strategy quickly 
*/
public abstract class Car {
	protected IBrakeStrategy brakeStrategy;

	public void applyBrake() {
		brakeStrategy.brake();
	}

	public void setBrakeStrategy(IBrakeStrategy brakeStrategy) {
		this.brakeStrategy = brakeStrategy;
	}

}
