package be.ugent.dominion.server;

import be.ugent.dominion.protocol.Event;

public class CellarState extends State {

    public CellarState(Server server) {
        super(server);
        discardCount = 0;
    }

    private int discardCount;

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendExecAction(currentPlayer, "Cellar", 0);
    }

    @Override
    protected void handClicked(int index) {
        PlayerModel player = server.currentPlayer();
        discardCount++;
        player.discardFromHand(index);
        server.send(Event.USER_AREA);
    }

    @Override
    protected void buttonPressed() {
        PlayerModel player = server.currentPlayer();
        player.draw(discardCount);
        server.send(Event.USER_AREA);
        server.endSpecialAction();
    }
}
