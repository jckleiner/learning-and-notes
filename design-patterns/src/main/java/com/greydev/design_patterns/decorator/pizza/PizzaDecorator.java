package com.greydev.design_patterns.decorator.pizza;

public abstract class PizzaDecorator implements IPizzaComponent {

	private IPizzaComponent pizzaToDecorate;

	public PizzaDecorator(IPizzaComponent pizzaToDecorate) {
		this.pizzaToDecorate = pizzaToDecorate;
	}

	@Override
	public double cost() {
		return pizzaToDecorate.cost();
	}

	@Override
	public String getDescription() {
		return pizzaToDecorate.getDescription();
	}

}
