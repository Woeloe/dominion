package be.ugent.dominion.cards;

public class Moat extends Action {

    public Moat () {
        super (2, 0, 2, 0, 0);
    }

    @Override
    public String getFooter() {
        return "Action - Reaction";
    }

    @Override
    public String getStyle() {
        return "reaction";
    }
}
