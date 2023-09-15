package be.ugent.dominion.cards;

public class Gold extends Treasure {

    public Gold () {
        super (6, 3);
    }

    @Override
    public int initialCount(int nrOfPlayers) {
        return 30;
    }
}
