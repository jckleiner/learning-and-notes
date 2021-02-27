package com.greydev.design_patterns.factory.abstractfactory.kingdom;

public interface KingdomFactory {

	Army getArmy();

	Castle getCastle();

	King getKing();
}
