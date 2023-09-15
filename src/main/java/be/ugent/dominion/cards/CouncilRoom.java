package be.ugent.dominion.cards;

import be.ugent.dominion.protocol.Event;
import be.ugent.dominion.server.PlayerModel;
import be.ugent.dominion.server.Server;
import be.ugent.dominion.server.State;

public class CouncilRoom extends Action {

    public CouncilRoom () {
        super (5, 0, 4, 0, 1);
    }

    @Override
    public State executeAdditionalAction(Server server) {
        for (PlayerModel other : server.getPlayerModels()) {
            if (other != server.currentPlayer()) {
                other.draw(1);
                server.send(other, Event.USER_AREA);
            }
        }
        return null;
    }
}
