package com.greydev.design_patterns.factory.simplefactory.pizza;

public class SimplePizzaFactory {

	public Pizza createPizza(String type) {
		Pizza pizza = null;

		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		}
		else if (type.equals("sucuk")) {
			pizza = new SucukPizza();
		}
		// more else if cases
		return pizza;
	}

}
