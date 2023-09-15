package be.ugent.dominion.cards;

abstract class ActionAttack extends Action {

    public ActionAttack(int price, int coinValue, int extraCards, int extraActions, int extraBuys) {
        super(price, coinValue, extraCards, extraActions, extraBuys);
    }

    @Override
    public String getFooter() {
        return "Action - Attack";
    }
}
