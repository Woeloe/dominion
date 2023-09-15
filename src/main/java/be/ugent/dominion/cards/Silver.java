package be.ugent.dominion.cards;

import be.ugent.dominion.server.PlayerModel;

public class Silver extends Treasure {

    public Silver () {
        super (3, 2);
    }

    @Override
    public int initialCount(int nrOfPlayers) {
        return 40;
    }

    @Override
    public void play(PlayerModel player) {
        player.addCoins(getCoinValue() + player.getFirstSilverCoins()); // effect v/e Merchant
    }
}
