package be.ugent.dominion.cards;

import java.util.HashMap;
import java.util.Map;

/**
 * Bevat een klassen methode die voor een gegeven naam de corresponderende kaart teruggeeft
 */
public final class Cards {

    private static final Card[] ALL_CARDS = {
            new Artisan(), new Bandit(), new Bureaucrat(), new Cellar(), new Chapel(),
            new Copper(), new CouncilRoom(), new Curse(), new Duchy(), new Estate(),
            new Festival(), new Gardens(), new Gold(), new Harbinger(), new Laboratory(),
            new Library(), new Market(), new Merchant(), new Militia(), new Mine(),
            new Moat(), new Moneylender(), new Poacher(), new Province(), new Remodel(),
            new Sentry(), new Silver(), new Smithy(), new ThroneRoom(), new Vassal(),
            new Village(), new Witch(), new Workshop()
    };

    private static final Map<String,Card> CARD_MAP = new HashMap<>();

    static {
        for (Card card : ALL_CARDS) {
            CARD_MAP.put(card.getName(), card);
        }
    }

    public static Card get(String name) {
        return CARD_MAP.get(name);
    }

}
