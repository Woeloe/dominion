package be.ugent.dominion.client;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Combineert de user interface-elementen voor één speler tot één paneel
 */
public class PlayerPanel extends VBox {

    private final UIPanel uiPanel;
    private final HBox center;

    public PlayerPanel(UserArea userArea, PlayArea playArea, UIPanel uiPanel) {
        this.uiPanel = uiPanel;
        this.center = new HBox(playArea);
        center.setId("center");
        HBox.setHgrow(playArea, Priority.ALWAYS);

        VBox.setVgrow(center, Priority.ALWAYS);
        getChildren().setAll(center, userArea);
    }

    public void showUIPanel() {
        center.getChildren().add(uiPanel);
    }
}
