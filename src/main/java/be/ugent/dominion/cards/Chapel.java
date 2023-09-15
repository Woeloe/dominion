package be.ugent.dominion.cards;

import be.ugent.dominion.server.ChapelState;
import be.ugent.dominion.server.Server;

public class Chapel extends Action {

    public Chapel () {
        super (2, 0, 0, 0, 0);
    }

    @Override
    public ChapelState executeAdditionalAction(Server server) {
        return new ChapelState(server);
    }

}
