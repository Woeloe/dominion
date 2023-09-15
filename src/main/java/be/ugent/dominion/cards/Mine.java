package be.ugent.dominion.cards;

import be.ugent.dominion.server.MineState;
import be.ugent.dominion.server.Server;

public class Mine extends Action {

    public Mine() {
        super(5, 0, 0, 0, 0);
    }

    @Override
    public MineState executeAdditionalAction(Server server) {
        return new MineState(server);
    }

}
