package be.ugent.dominion.client;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverDialog extends Stage {

    public GameOverDialog(int[] points) {
        initModality(Modality.WINDOW_MODAL);
        setScene(new Scene(new GameOverPanel(points)));
    }

    private static class GameOverPanel extends VBox {

        public GameOverPanel(int[] points) {
            getStylesheets().add("be/ugent/dominion/game-over.css");
            Label label = new Label("Victory points");
            getChildren().add(label);
            VBox.setVgrow(label, Priority.ALWAYS);
            for (int i = 0; i < points.length; i++) {
                getChildren().add(new Label((i + 1) + ": VP = " + points[i]));
            }
        }

    }


}
