package be.ugent.dominion.cards;

import be.ugent.dominion.server.RemodelState;
import be.ugent.dominion.server.Server;

public class Remodel extends Action {

    public Remodel () {
        super (4, 0, 0, 0, 0);
    }

    @Override
    public RemodelState executeAdditionalAction(Server server) {
        return new RemodelState(server);
    }
}
