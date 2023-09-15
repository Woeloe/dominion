package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.cards.Cards;
import be.ugent.dominion.protocol.Event;

/**
 * Toestand waarin de huidige speler een kaart koopt
 */
public class BuyState extends State {

    public BuyState(Server server) {
        super(server);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendToAll(Event.BUY_PHASE, currentPlayer.getId());
    }

    @Override
    protected void handClicked(int index) {
        PlayerModel player = server.currentPlayer();
        if (!player.noBuysLeft()) {
            Card card = player.getCardFromHand(index);
            if (card.isTreasure()) {
                player.playCard(card, index);
                server.sendToAll(Event.PLAY_AREA);
                server.send(Event.USER_AREA, Event.ABC);
            }
        }
    }

    private void nextPhase() {
        server.currentPlayer().cleanup();
        server.sendToAll(Event.PLAY_AREA);
        if (server.isGameOver()) {
            server.sendToAll(Event.GAME_OVER);
        } else {
            server.nextPlayer();
        }
    }

    @Override
    protected void supplyClicked(String cardName) {
        PlayerModel player = server.currentPlayer();
        Card card = Cards.get(cardName);
        if (player.isValidBuy(card)) {
            player.buy(card);
            server.sendToAll(Event.SUPPLY);
            if (player.noBuysLeft()) {
                nextPhase();
            }
            server.send(Event.USER_AREA, Event.ABC);
        }
    }

    @Override
    protected void buttonPressed() {
        PlayerModel player = server.currentPlayer();
        player.clearBuys();
        nextPhase();
        server.send(Event.USER_AREA, Event.ABC);
    }
}
