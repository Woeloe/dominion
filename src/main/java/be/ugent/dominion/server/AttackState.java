package be.ugent.dominion.server;

import java.util.HashSet;
import java.util.Set;

public class AttackState extends State {

    protected final Set<PlayerModel> activePlayers;

    public AttackState(Server server) {
        super(server);
        this.activePlayers = new HashSet<>(server.getPlayerModels());
    }

    protected void playerFinished(PlayerModel player) {
        activePlayers.remove(player);
        if (activePlayers.isEmpty()) {
            server.endSpecialAction();
        }
    }
}
