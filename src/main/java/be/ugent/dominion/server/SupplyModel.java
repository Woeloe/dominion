package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.cards.Cards;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/* Houdt bij hoeveel kaarten er van elke soort zich in de voorraad (supply) bevinden.
 * Brengt clients op de hoogte wanneer aantallen veranderen.
 */
public class SupplyModel {

    private final Map<String, Integer> countsMap;

    public SupplyModel() {
        this.countsMap = new HashMap<>();
    }

    public void addTypes(String[] names, int nrOfPlayers) {
        for (String name : names) {
            countsMap.put(name, Cards.get(name).initialCount(nrOfPlayers));
        }
    }

    public boolean hasCard (String name) {
        return countsMap.get(name) > 0;
    }

    public Card take(String name) {
        countsMap.put(name, countsMap.get(name) - 1);
        return Cards.get(name);
    }

    private boolean isProvincePileEmpty() {
        return countsMap.get("Province") == 0;
    }

    private int emptyPileCount() {
        int total = 0;
        for (Integer value : countsMap.values()) {
            if (value == 0) {
                total++;
            }
        }
        return total;
    }

    public boolean isGameOver() {
        return isProvincePileEmpty() || emptyPileCount() >= 3;
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(countsMap);
    }

    public int nrOfEmptyPiles() {
        return (int)countsMap.values().stream().filter(count -> count == 0).count();
    }
}
