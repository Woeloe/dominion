package be.ugent.dominion.cards;

import be.ugent.dominion.server.PoacherState;
import be.ugent.dominion.server.Server;

public class Poacher extends Action {

    public Poacher () {
        super (4, 1, 1, 1, 0);
    }

    @Override
    public PoacherState executeAdditionalAction(Server server) {
        return new PoacherState(server);
    }
}
