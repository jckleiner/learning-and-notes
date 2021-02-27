package com.greydev.design_patterns.factory.abstractfactory.kingdom;

public class ElfKingdomFactory implements KingdomFactory {

	@Override
	public Army getArmy() {
		return new ElfArmy();
	}

	@Override
	public Castle getCastle() {
		return new ElfCastle();
	}

	@Override
	public King getKing() {
		return new ElfKing();
	}

}
