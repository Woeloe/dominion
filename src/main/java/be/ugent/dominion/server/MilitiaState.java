package be.ugent.dominion.server;

import be.ugent.dominion.protocol.Event;

public class MilitiaState extends AttackState {

    public MilitiaState(Server server) {
        super(server);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        this.activePlayers.remove(currentPlayer);
        for (PlayerModel player : server.getPlayerModels()) {
            if (player.getHand().nrOfCards() <= 3) {
                activePlayers.remove(player);
            }
        }
        if (activePlayers.isEmpty()) {
            server.endSpecialAction();
        } else {
            for (PlayerModel player : activePlayers) {
                server.sendExecAction(player, "Militia", player.getHand().containsMoat() ? 2 : 1);
            }
            server.sendExecAction(currentPlayer, "Militia", 0);
        }

    }

    @Override
    public void handClicked(PlayerModel player, int index) {
        if (activePlayers.contains(player)) {
            player.discardFromHand(index);
            server.send(player, Event.USER_AREA);
            if (player.getHand().nrOfCards() <= 3) {
                playerFinished(player);
            }
        }
    }

    @Override
    public void buttonPressed(PlayerModel player) {
        if (activePlayers.contains(player) && player.getHand().containsMoat()) {
            playerFinished(player);
            player.sendLogEvent(Event.REVEALS_CARD, "Moat");
        }
    }
}
