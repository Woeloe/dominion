package be.ugent.dominion.server;

import be.ugent.dominion.protocol.Event;

public class ArtisanStateX extends State {

    public ArtisanStateX(Server server) {
        super(server);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendExecAction(currentPlayer, "Artisan", 1);
    }

    @Override
    protected void handClicked(int index) {
        server.currentPlayer().topDeck(index);
        server.send(Event.USER_AREA);
        server.endSpecialAction();
    }
}
