package be.ugent.dominion.server;

public class RemodelStateX extends GainState {

    public RemodelStateX(Server server, int maxPrice) {
        super(server, maxPrice);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendExecAction(currentPlayer, "Remodel", maxPrice);
    }

}
