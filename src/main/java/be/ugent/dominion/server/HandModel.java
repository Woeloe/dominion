package be.ugent.dominion.server;

import java.util.Objects;

public class HandModel extends CardListModel {

    public int nrOfCards () {
        return (int) contents.stream().filter(Objects::nonNull).count();
    }

    public boolean containsMoat() {
        return contents.stream().anyMatch(card -> card.getName().equals("Moat"));
    }
}
