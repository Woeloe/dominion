package be.ugent.dominion.client;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.cards.Cards;
import be.ugent.dominion.protocol.Command;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

public class SupplyPanel extends GridPane {

    private final Map<String, Pile> piles;

    public SupplyPanel(String[] cards, int nrOfColumns, double scale, Client client) {
        this.piles = new HashMap<>();

        for (int i = 0; i < cards.length; i++) {
            Card card = Cards.get(cards[i]);
            HalfCard halfCard = new HalfCard(card, scale);
            halfCard.setOnMouseClicked(
                    event -> {
                        event.consume();
                        client.send(Command.SUPPLY, ((HalfCard) event.getSource()).getCard().getName());
                    }
            );

            Pile pile = new Pile(halfCard);
            pile.setCount(0);
            add(pile, i % nrOfColumns, i / nrOfColumns);
            piles.put(card.getName(), pile);
        }
    }

    public void setCounts(Map<String, Integer> map) {
        piles.forEach((k, v) -> v.setCount(map.get(k)));
    }

}
