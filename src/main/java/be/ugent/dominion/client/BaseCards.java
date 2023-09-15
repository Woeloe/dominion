package be.ugent.dominion.client;

import be.ugent.dominion.cards.CardSets;

/**
 * Het gedeelte van de 'supply' dat victory, treasure en curse-kaarten bevat.
 */
public class BaseCards extends SupplyPanel {

    public BaseCards(Client client) {
        super(CardSets.BASE_CARDS, 2, 0.7, client);
    }
}
