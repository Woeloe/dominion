package be.ugent.dominion.cards;

import be.ugent.dominion.server.PlayerModel;

abstract class Treasure extends Card {

    public Treasure(int price, int coinValue) {
        super(price, coinValue);
    }

    @Override
    public String getFooter() {
        return "Treasure";
    }

    @Override
    public String getStyle() {
        return "treasure";
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public void play(PlayerModel player) {
        player.addCoins(getCoinValue());
    }
}
