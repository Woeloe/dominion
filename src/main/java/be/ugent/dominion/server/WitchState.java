package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.protocol.Event;

public class WitchState extends AttackState {

    public WitchState(Server server) {
        super(server);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        this.activePlayers.remove(currentPlayer);
        if (activePlayers.isEmpty()) {
            // (Enkel wanneer slechts 1 speler.)
            server.endSpecialAction();
        } else {
            for (PlayerModel player : activePlayers) {
                server.sendExecAction(player, "Witch", 1);
            }
            server.sendExecAction(currentPlayer, "Witch", 0);
        }
    }

    @Override
    public void handClicked(PlayerModel player, int index) {
        // Klikken op een Moat zorgt vermijdt de vloek
        if (activePlayers.contains(player)) {
            Card card = player.getCardFromHand(index);
            if (card.getName().equals("Moat")) {
                player.sendLogEvent(Event.REVEALS_CARD, "Moat");
                playerFinished(player);
            }
        }
    }

    @Override
    public void buttonPressed(PlayerModel player) {
        // Klikken op de knop activeert de vloek (als er nog een kaart over is)
        if (server.getSupply().hasCard("Curse")) {
            player.gain("Curse");
            server.send(player,Event.SUPPLY);
            server.send(player,Event.USER_AREA);
            playerFinished(player);
        }
    }
}
