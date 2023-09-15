package be.ugent.dominion.client;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Map;

public class MainPanel extends HBox {

    private final VBox right;

    private final BaseCards baseCards;
    private Kingdom kingdom;

    public MainPanel(LogPanel logPanel, BaseCards baseCards, PlayerPanel playerPanel) {

        this.baseCards = baseCards;

        getStylesheets().add("be/ugent/dominion/dominion.css");
        VBox left = new VBox(baseCards, logPanel);
        left.setId("left");

        this.right = new VBox(playerPanel);
        right.setId("right");
        VBox.setVgrow(playerPanel, Priority.SOMETIMES);

        getChildren().addAll(left, right);
        HBox.setHgrow(right, Priority.ALWAYS);
    }

    public void setKingdom (Kingdom kingdom) {
        this.kingdom = kingdom;
        right.getChildren().add(0, kingdom);
        VBox.setVgrow(kingdom, Priority.ALWAYS);
    }

    public void setSupplyCounts(Map<String, Integer> counts) {
        kingdom.setCounts(counts);
        baseCards.setCounts(counts);
    }


}
