package be.ugent.dominion.server;

public class DiscardModel extends CardListModel {

    public String topCard() {
        int size = getContents().size();
        if (size == 0) {
            return "none";
        } else {
            return getContents().get(size - 1).getName();
        }
    }
}
