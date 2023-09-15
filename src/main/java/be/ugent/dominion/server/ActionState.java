package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.protocol.Event;

public class ActionState extends State {

    public ActionState(Server server) {
        super(server);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        if (currentPlayer.noActionsLeft()) {
            server.setState(new BuyState(server));
        } else {
            server.sendToAll(Event.ACTION_PHASE, currentPlayer.getId());
        }
    }

    @Override
    protected void handClicked(int index) {
        PlayerModel player = server.currentPlayer();
        if (!player.noActionsLeft()) {
            Card card = player.getCardFromHand(index);
            if (card.isAction()) {
                player.playCard(card, index);
                server.sendToAll(Event.PLAY_AREA);
                server.send(Event.USER_AREA, Event.ABC);
                State state = card.executeAdditionalAction(server);
                if (state == null) {
                    server.endSpecialAction();
                } else {
                    server.setState(state);
                }
            }
        }
    }

    @Override
    protected void buttonPressed() {
        PlayerModel player = server.currentPlayer();
        player.clearActions();
        server.send(Event.ABC);
        server.setState(new BuyState(server));
    }
}
