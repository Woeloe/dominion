package be.ugent.dominion.server;

import be.ugent.dominion.protocol.Event;

/**
 * De gebruiker mag tot 4 kaarten weggooien.
 */
public class ChapelState extends State {

    private int remaining;

    public ChapelState(Server server) {
        super(server);
        this.remaining = 4;
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendExecAction(currentPlayer, "Chapel", 0);
    }

    @Override
    protected void handClicked(int index) {
        PlayerModel player = server.currentPlayer();
        player.trash(index);
        server.send(Event.USER_AREA);
        remaining --;
        if (remaining <= 0) {
            server.endSpecialAction();
        }
    }

    @Override
    protected void buttonPressed() {
        server.endSpecialAction();
    }
}
