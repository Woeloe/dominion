package be.ugent.dominion.cards;

import be.ugent.dominion.server.ArtisanState;
import be.ugent.dominion.server.Server;

public class Artisan extends Action {

    public Artisan () {
        super (6, 0, 0, 0, 0);
    }

    @Override
    public ArtisanState executeAdditionalAction(Server server) {
        return new ArtisanState(server);
    }
}
