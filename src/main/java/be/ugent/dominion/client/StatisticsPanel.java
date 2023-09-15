package be.ugent.dominion.client;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StatisticsPanel extends GridPane {

    private final Label[] abcLabels;

    public StatisticsPanel () {
        addColumn (0,
                new Label ("Actions"),
                new Label ("Buys"),
                new Label("Coins")
                );
        this.abcLabels = new Label[3];
        for (int i = 0; i < 3; i++) {
            abcLabels[i] = new Label ("0");
            abcLabels[i].getStyleClass().add("abc");
            add(abcLabels[i], 1, i);
        }
    }

    public void setABC(int[] abc) {
        for (int i = 0; i < 3; i++) {
            abcLabels[i].setText("" + abc[i]);
        }
    }

}
