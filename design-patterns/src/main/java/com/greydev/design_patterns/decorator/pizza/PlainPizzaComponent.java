package com.greydev.design_patterns.decorator.pizza;

public class PlainPizzaComponent implements IPizzaComponent {

	@Override
	public double cost() {
		return 5; // plain pizza costs 5 dollars
	}

	@Override
	public String getDescription() {
		return "Plain pizza";
	}

}
