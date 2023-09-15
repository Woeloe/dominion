package be.ugent.dominion.protocol;

/**
 * Gebeurtenissen gerapporteerd door de server aan de client
 */
public enum Event {

    SUPPLY,        // De aantallen in de voorraad zijn veranderd
    PLAY_AREA,     // Het spelgebied is veranderd
    USER_AREA,     // Pak, hand of aflegstapel zijn veranderd
    ABC,           // De ABC-gegevens zijn veranderd
    GAME_STARTED,  // Het spel wordt gestart. Client kan nu bijv. een koninkrijk opvragen.
    GAME_OVER,     // Het spel is ten einde

    ACTION_PHASE,  // De speler aan zet mag een actiekaart spelen
    BUY_PHASE,     // De speler aan zet begint de koopfase van het spel
    CLEANUP_PHASE, // De speler aan zet beëindigt zijn zet
    EXEC_ACTION,   // Een afzonderlijke 'fase' wordt gestart voor een bepaalde actiekaart

    PLAYS_CARD,    // De speler speelt een kaart uit met de gegeven naam
    BUYS_CARD,     // De speler koopt een kaart met de gegeven naam
    GAINS_CARD,    // De speler wint een kaart met de gegeven naam
    TRASHES_CARD,  // De speler gooit een kaart weg met de gegeven naam
    REVEALS_CARD,  // De speler toont de kaart met de gegeven naam
    DRAWS_CARDS,   // De speler trekt een aantal kaarten (pak naar hand)


}
