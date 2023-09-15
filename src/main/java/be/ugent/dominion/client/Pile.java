package be.ugent.dominion.client;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Stapel kaarten waar een aantal aan verbonden is.
 */
public class Pile extends AnchorPane {

    private final Label countLabel;

    public Pile(Node node) {
        this.countLabel = new Label();
        this.countLabel.getStyleClass().add("count");
        setCount(0);

        setTopAnchor(node, 0.0);
        setLeftAnchor(node, 0.0);
        setTopAnchor(countLabel, -4.0);
        setLeftAnchor(countLabel, -4.0);

        getChildren().addAll(node, countLabel);
    }

    public void setCount (int count) {
        countLabel.setText("" + count);
        setDisable(count == 0);
    }

}
