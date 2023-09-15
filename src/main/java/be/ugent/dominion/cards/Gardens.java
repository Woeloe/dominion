package be.ugent.dominion.cards;

public class Gardens extends Victory {

    public Gardens () {
        super (4, 0);
    }

    @Override
    public int getVictoryPoints(int fullDeckSize) {
        return fullDeckSize / 10 ;
    }
}
