package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;

public class MineStateX extends GainState {

    public MineStateX(Server server, int maxPrice) {
        super(server, maxPrice);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendExecAction(currentPlayer, "Mine", maxPrice);
    }

    @Override
    public boolean extraCondition(Card card) {
        return card.isTreasure();
    }

    @Override
    public void gain(PlayerModel player, Card card) {
        player.gainToHand(card.getName());
    }
}
