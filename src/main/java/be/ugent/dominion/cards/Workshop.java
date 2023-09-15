package be.ugent.dominion.cards;

import be.ugent.dominion.server.Server;
import be.ugent.dominion.server.WorkshopState;

public class Workshop extends Action {

    public Workshop() {
        super(3, 0, 0, 0, 0);
    }

    @Override
    public WorkshopState executeAdditionalAction(Server server) {
        return new WorkshopState(server);
    }
}
