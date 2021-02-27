package com.greydev.design_patterns.factory.abstractfactory.kingdom;

public class FactoryMaker {

	public enum KingdomType {
		ELF, ORC
	}

	public static KingdomFactory makeKingdomFactory(KingdomType type) {
		switch (type) {
		case ELF:
			return new ElfKingdomFactory();
		case ORC:
			return new OrcKingdomFactory();
		default:
			throw new IllegalArgumentException("KingdomType not supported.");
		}
	}
}
