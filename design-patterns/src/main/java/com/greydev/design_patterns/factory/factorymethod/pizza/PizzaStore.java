package com.greydev.design_patterns.factory.factorymethod.pizza;

// abstract creator
public abstract class PizzaStore {

	public final Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);

		// pizza.bake();
		// pizza.cut();
		// pizza.box();
		return pizza;
	}

	// factory method
	protected abstract Pizza createPizza(String type);
}
