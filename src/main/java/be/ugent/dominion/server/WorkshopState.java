package be.ugent.dominion.server;

public class WorkshopState extends GainState {

    public WorkshopState(Server server) {
        super(server, 4);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendExecAction(currentPlayer, "Workshop", 0);
    }

}
