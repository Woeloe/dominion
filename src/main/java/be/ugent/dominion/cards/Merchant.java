package be.ugent.dominion.cards;

import be.ugent.dominion.server.Server;
import be.ugent.dominion.server.State;

public class Merchant extends Action {

    public Merchant () {
        super (3, 0, 1, 1, 0);
    }

    @Override
    public State executeAdditionalAction(Server server) {
        server.currentPlayer().addFirstSilverCoin();
        return null;
    }
}
