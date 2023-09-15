package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Houdt bij welke (en hoeveel) kaarten er in het pak (deck) zitten. Laat
 * clients weten wanneer er iets verandert.
 */
public class DeckModel extends CardListModel {

    /**
     * Neem de gegeven lijst van kaarten, schud ze en plaats ze onder de kaarten
     * die nog in het huidige pak zitten.
     */
    public void addUnder(List<Card> extra) {
        List<Card> newContents = new ArrayList<>(extra);
        Collections.shuffle(newContents);
        newContents.addAll(contents);
        contents = newContents;
    }

    /**
     * Haal 3 landgoed- en 7 koper-kaarten uit de voorraad en stop ze in dit pak.
     */
    public void startGame(SupplyModel supply) {
        for (int i = 0; i < 3; i++) {
            contents.add (supply.take("Estate"));
        }
        for (int i = 0; i < 7; i++) {
            contents.add (supply.take("Copper"));
        }
        Collections.shuffle(contents);
    }

    /**
     * Plaatst de volgende vijf kaarten in de hand.
     */

    public void drawHand(HandModel hand, DiscardModel discard, int nrOfCards) {
        if (contents.size() < nrOfCards) {
            addUnder (discard.getContents());
            discard.getContents().clear();
        }
        int nr = Math.min(contents.size(), nrOfCards);
        for (int i = 0; i < nr; i++) {
            hand.getContents().add(contents.remove(contents.size() - 1));
        }
    }

    public String getCountAsString() {
        return "" + contents.size();
    }
}
