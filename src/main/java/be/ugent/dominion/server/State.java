package be.ugent.dominion.server;

/**
 * Houdt de huidige toestand bij van het spel
 */
public abstract class State {

    protected final Server server;

    public State(Server server) {
        this.server = server;
    }

    public void initState(PlayerModel currentPlayer) {
        // doet niets
    }

    protected void handClicked(int index) {
        // doet niets
    }

    public void handClicked(PlayerModel player, int index) {
        if (player == server.currentPlayer()) {
            handClicked(index);
        }
    }

    protected void supplyClicked(String cardName) {
        // doet niets
    }

    public void supplyClicked(PlayerModel player, String cardName) {
        if (player == server.currentPlayer()) {
            supplyClicked(cardName);
        }

    }

    protected void buttonPressed() {
        // doet niets
    }

    public void buttonPressed(PlayerModel player) {
        if (player == server.currentPlayer()) {
            buttonPressed();
        }
    }

}
