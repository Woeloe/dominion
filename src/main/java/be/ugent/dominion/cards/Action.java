package be.ugent.dominion.cards;

import be.ugent.dominion.server.PlayerModel;

abstract class Action extends Card {

    private final int extraCards;
    private final int extraActions;
    private final int extraBuys;

    public Action(int price, int coinValue, int extraCards, int extraActions, int extraBuys) {
        super(price, coinValue);
        this.extraCards = extraCards;
        this.extraActions = extraActions;
        this.extraBuys = extraBuys;
    }

    @Override
    public boolean isAction () {
        return true;
    }

    @Override
    public String getFooter() {
        return "Action";
    }

    @Override
    public String getStyle() {
        return "action";
    }

    @Override
    public void play(PlayerModel player) {
        player.draw(extraCards);
        player.addCoins(getCoinValue());
        player.addActions(extraActions);
        player.addBuys(extraBuys);
    }
}
