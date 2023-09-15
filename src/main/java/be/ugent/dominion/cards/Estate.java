package be.ugent.dominion.cards;

public class Estate extends Victory {

    public Estate () {
        super (2, 1);
    }

    @Override
    public int initialCount(int nrOfPlayers) {
         // 3 kaarten per speler worden in het begin naar de hand verplaatst
        return super.initialCount(nrOfPlayers) + 3 * nrOfPlayers;
    }
}
