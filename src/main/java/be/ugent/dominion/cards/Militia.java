package be.ugent.dominion.cards;

import be.ugent.dominion.server.MilitiaState;
import be.ugent.dominion.server.Server;

public class Militia extends ActionAttack {

    public Militia () {
        super (4, 2, 0, 0, 0);
    }

    @Override
    public MilitiaState executeAdditionalAction(Server server) {
        return new MilitiaState(server);
    }
}
