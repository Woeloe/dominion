package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.protocol.Event;

public class RemodelState extends State {

    public RemodelState(Server server) {
        super(server);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        // TODO cannot trash a card from an empty hand
        server.sendExecAction(currentPlayer, "Remodel", 0);
    }

    @Override
    protected void handClicked(int index) {
        PlayerModel player = server.currentPlayer();
        Card card = player.trash(index);
        server.send(Event.USER_AREA);
        server.setState(new RemodelStateX(server, card.getPrice() + 2));
    }
}
