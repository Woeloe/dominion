package be.ugent.dominion.cards;

import be.ugent.dominion.server.MoneylenderState;
import be.ugent.dominion.server.Server;

public class Moneylender extends Action {

    public Moneylender () {
        super (4, 0, 0, 0, 0);
    }

    @Override
    public MoneylenderState executeAdditionalAction(Server server) {
        return new MoneylenderState(server);
    }
}
