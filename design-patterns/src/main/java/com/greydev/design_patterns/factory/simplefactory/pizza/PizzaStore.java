package com.greydev.design_patterns.factory.simplefactory.pizza;

public class PizzaStore {

	SimplePizzaFactory pizzaFactory;

	public PizzaStore(SimplePizzaFactory pizzaFactory) {
		this.pizzaFactory = pizzaFactory;
	}

	public Pizza orderPizza(String type) {
		Pizza pizza = pizzaFactory.createPizza(type);

		// pizza.bake();
		// pizza.cut();
		// pizza.box();
		return pizza;
	}
}
