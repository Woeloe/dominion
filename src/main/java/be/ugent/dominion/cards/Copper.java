package be.ugent.dominion.cards;

public class Copper extends Treasure {

    public Copper () {
        super (0, 1);
    }

    @Override
    public int initialCount(int nrOfPlayers) {
        return 60; // 7 kaarten per speler worden in het begin naar de hand verplaatst
    }
}
