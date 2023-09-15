package be.ugent.dominion.cards;

public class Curse extends Victory {

    public Curse () {
        super (0, -1);
    }

    @Override
    public String getFooter() {
        return "Curse";
    }

    @Override
    public String getStyle() {
        return "curse";
    }

    @Override
    public int initialCount(int nrOfPlayers) {
        return nrOfPlayers == 1 ? 10 : 10 * nrOfPlayers - 10;
    }
}
