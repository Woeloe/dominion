package be.ugent.dominion.client;

public class Deck extends Pile {

    // afzonderlijke klasse omwille van CSS

    public Deck() {
        super(FullCard.backOfCard());
    }
}
