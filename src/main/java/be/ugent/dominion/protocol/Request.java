package be.ugent.dominion.protocol;

/**
 * Verzoeken van client naar server
 */
public enum Request {

    KINGDOM,   // Kaarten in het koninkrijk
    SUPPLY,    // Voor elke kaart, het aantal in de voorraad
    DECK,      // Het aantal kaarten in het pak v/d speler met gegeven nummer
    HAND,      // De (namen van de) kaarten in de hand, inclusief null voor een lege plaats
    DISCARD,   // Naam van de bovenste afgelegde kaart (discard). String "none" wanneer de stapel leeg is
    ABC,       // Aantal overblijvende actions/buys/coins (= ABC-gegevens)
    PLAY_AREA, // Namen van kaarten in het spelgebied
    VICTORY_POINTS, // Winstpunten voor elke speler, begint met speler 1


}
