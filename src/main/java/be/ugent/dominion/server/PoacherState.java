package be.ugent.dominion.server;

import be.ugent.dominion.protocol.Event;

public class PoacherState extends State {

    private int toDiscard;

    private boolean endsDiscard() {
        return toDiscard <= 0 || server.currentPlayer().getHand().nrOfCards() <= 0;
    }

    public PoacherState(Server server) {
        super(server);
        this.toDiscard = server.nrOfEmptySupplyPiles();
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        if (endsDiscard()) {
            server.endSpecialAction();
        } else {
            server.sendExecAction(currentPlayer, "Poacher", toDiscard);
        }
    }

    @Override
    protected void handClicked(int index) {
        server.currentPlayer().discardFromHand(index);
        server.send(Event.USER_AREA);
        toDiscard--;
        if (endsDiscard()) {
            server.endSpecialAction();
        }
    }
}
