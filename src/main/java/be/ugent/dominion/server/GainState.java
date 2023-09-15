package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.cards.Cards;
import be.ugent.dominion.protocol.Event;

class GainState extends State {

    protected final int maxPrice;

    public GainState(Server server, int maxPrice) {
        super(server);
        this.maxPrice = maxPrice;
    }

    public boolean extraCondition(Card card) {
        return true;
    }

    public void gain (PlayerModel player, Card card) {
        player.gain(card.getName());
    }

    @Override
    protected void supplyClicked(String cardName) {
        PlayerModel player = server.currentPlayer();
        Card card = Cards.get(cardName);
        if (card.getPrice() <= maxPrice && extraCondition(card)) {
            gain (player, card); // overschreven in MineStateX
            server.endSpecialAction();
            server.sendToAll(Event.SUPPLY);
            server.send(Event.USER_AREA);
        }
    }
}
