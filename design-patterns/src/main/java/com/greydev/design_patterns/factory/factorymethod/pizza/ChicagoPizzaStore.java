package com.greydev.design_patterns.factory.factorymethod.pizza;

//concrete creator
public class ChicagoPizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza = null;

		if (type.equals("cheese")) {
			pizza = new ChicagoStyleCheesePizza();
			System.out.println("Creating ChicagoStyleCheesePizza...");
		}
		else if (type.equals("sucuk")) {
			pizza = new ChicagoStyleSucukPizza();
			System.out.println("Creating ChicagoStyleSucukPizza...");
		}
		// more else if cases
		return pizza;
	}

}
