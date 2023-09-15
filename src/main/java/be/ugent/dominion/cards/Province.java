package be.ugent.dominion.cards;

public class Province extends Victory {

    public Province () {
        super (8, 6);
    }

    @Override
    public int initialCount(int nrOfPlayers) {
        return (nrOfPlayers <= 4
                ? super.initialCount(nrOfPlayers)
                : 3*nrOfPlayers
        );
    }
}
