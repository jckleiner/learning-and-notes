package com.greydev.design_patterns.decorator.pizza;

public class CheeseDecorator extends PizzaDecorator {

	public CheeseDecorator(IPizzaComponent pizzaToDecorate) {
		super(pizzaToDecorate);
		System.out.println("Adding cheese...");
	}

	@Override
	public double cost() {
		return super.cost() + 1;
	}

	@Override
	public String getDescription() {
		return super.getDescription() + " + cheese";
	}

}
