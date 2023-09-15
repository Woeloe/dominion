package be.ugent.dominion.cards;

import be.ugent.dominion.server.CellarState;
import be.ugent.dominion.server.Server;

public class Cellar extends Action {

    public Cellar () {
        super (2, 0, 0, 1, 0);
    }

    @Override
    public CellarState executeAdditionalAction(Server server) {
        return new CellarState(server);
    }

}
