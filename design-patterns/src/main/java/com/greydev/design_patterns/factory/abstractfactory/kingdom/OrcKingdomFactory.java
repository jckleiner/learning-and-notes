package com.greydev.design_patterns.factory.abstractfactory.kingdom;

public class OrcKingdomFactory implements KingdomFactory {

	@Override
	public Army getArmy() {
		return new OrcArmy();
	}

	@Override
	public Castle getCastle() {
		return new OrcCastle();
	}

	@Override
	public King getKing() {
		return new OrcKing();
	}

}
