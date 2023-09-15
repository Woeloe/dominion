package be.ugent.dominion.client;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.List;

public class UserArea extends HBox {

    private final Hand hand;
    private final DiscardPile discardPile;
    private final Deck deck;

    public UserArea(Client client) {
        this.hand = new Hand(client);
        this.discardPile = new DiscardPile();
        this.deck = new Deck();
        HBox.setHgrow(hand, Priority.ALWAYS);
        getChildren().setAll(deck, hand, discardPile);
    }

    public void changed(int deckSize, List<String> handCards, String discardCard) {
        discardPile.setCard(discardCard);
        deck.setCount(deckSize);
        hand.setCards(handCards);
    }
}
