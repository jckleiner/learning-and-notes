package com.greydev.design_patterns.decorator.pizza;

public class SucukDecorator extends PizzaDecorator {

	public SucukDecorator(IPizzaComponent pizzaToDecorate) {
		super(pizzaToDecorate);
		System.out.println("Adding sucuk...");
	}

	@Override
	public double cost() {
		return super.cost() + 2;
	}

	@Override
	public String getDescription() {
		return super.getDescription() + " + sucuk";
	}

}