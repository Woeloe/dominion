package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.protocol.Event;

public class MineState extends State {

    public MineState(Server server) {
        super(server);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendExecAction(currentPlayer, "Mine", 0);
    }

    @Override
    protected void handClicked(int index) {
        PlayerModel player = server.currentPlayer();
        Card card = player.getCardFromHand(index);
        if (card.isTreasure()) {
            player.trash(index);
            server.send(Event.USER_AREA);
            server.setState(new MineStateX(server, card.getPrice() + 3));
        }
    }

    @Override
    protected void buttonPressed() {
        server.endSpecialAction();
    }
}
