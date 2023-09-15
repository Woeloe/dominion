package be.ugent.dominion.cards;

abstract class Victory extends Card {

    private final int victoryPoints;

    public Victory(int price, int victoryPoints) {
        super(price, 0);
        this.victoryPoints = victoryPoints;
    }

    @Override
    public String getFooter() {
        return "Victory";
    }

    @Override
    public String getStyle() {
        return "victory";
    }

    @Override
    public int initialCount(int nrOfPlayers) {
        return nrOfPlayers <= 2 ? 8 : 12;
    }

    @Override
    public int getVictoryPoints(int fullDeckSize) {
        return victoryPoints;
    }
}
