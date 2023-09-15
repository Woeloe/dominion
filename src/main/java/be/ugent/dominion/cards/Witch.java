package be.ugent.dominion.cards;

import be.ugent.dominion.server.Server;
import be.ugent.dominion.server.WitchState;

public class Witch extends ActionAttack {

    public Witch () {
        super (5, 0, 2, 0, 0);
    }

    @Override
    public WitchState executeAdditionalAction(Server server) {
        return new WitchState(server);
    }
}
