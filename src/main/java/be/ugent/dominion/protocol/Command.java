package be.ugent.dominion.protocol;

/**
 * Opdrachten van client naar server
 */
public enum Command {
    HAND,        // De kaart met gegeven index in de hand werd aangeklikt.
    SUPPLY,      // De kaart met gegeven *naam* werd aangeklikt in de voorraad
    BUTTON,      // De knop op de gebruikersinterface werd ingedrukt
    TERMINATE,   // De client vraagt om het programma af te sluiten.
}
