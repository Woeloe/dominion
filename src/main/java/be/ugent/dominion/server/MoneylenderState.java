package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.protocol.Event;
import be.ugent.dominion.server.PlayerModel;
import be.ugent.dominion.server.State;

public class MoneylenderState extends State {

    public MoneylenderState(Server server) {
        super(server);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendExecAction(currentPlayer, "Moneylender", 0);
    }

    @Override
    protected void handClicked(int index) {
        PlayerModel player = server.currentPlayer();
        Card card = player.getCardFromHand(index);
        if (card.getName().equals("Copper")) {
            player.trash(index);
            player.addCoins(3);
            server.send(Event.USER_AREA, Event.ABC);
            server.endSpecialAction();
        }
    }

    @Override
    protected void buttonPressed() {
        server.endSpecialAction();
    }
}
