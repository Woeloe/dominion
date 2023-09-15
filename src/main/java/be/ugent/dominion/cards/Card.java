package be.ugent.dominion.cards;

import be.ugent.dominion.server.PlayerModel;
import be.ugent.dominion.server.Server;
import be.ugent.dominion.server.State;

public abstract class Card {

    private final int price;
    private final int coinValue;
    private final String name;

    public Card(int price, int coinValue) {
        this.price = price;
        this.coinValue = coinValue;
        String className = getClass().getName();
        this.name =  className.substring(className.lastIndexOf('.') + 1);
    }

    public int getPrice() {
        return price;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public String getName() {
        return name;
    }

    public abstract String getFooter();

    public abstract String getStyle();

    public int initialCount(int nrOfPlayers) {
        return 10;
    }

    public void play(PlayerModel player) {
        // doet niets
    }

    /**
     * Is dit een actiekaart?
     */
    public boolean isAction () {
        return false;
    }

    /**
     * Is dit een geldkaart?
     */
    public boolean isTreasure() {
        return false;
    }

    /**
     * Overschreven door actiekaarten met bijzonder acties
     */
    public State executeAdditionalAction(Server server) {
        return null;
    }

    public int getVictoryPoints(int fullDeckSize) {
        return 0;
    }

}
