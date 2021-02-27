package com.greydev.design_patterns.factory.factorymethod.pizza;

// concrete creator
public class NYPizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza = null;

		if (type.equals("cheese")) {
			pizza = new NYStyleCheesePizza();
			System.out.println("Creating NYStyleCheesePizza...");
		}
		else if (type.equals("sucuk")) {
			pizza = new NYStyleSucukPizza();
			System.out.println("Creating NYStyleSucukPizza...");
		}
		// more else if cases
		return pizza;
	}

}
