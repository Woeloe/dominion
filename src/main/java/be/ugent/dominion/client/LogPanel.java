package be.ugent.dominion.client;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class LogPanel extends VBox {


    public record LogRecord(int player, String message) {
    }

    private final List<Image> IMAGES;
    private final ListView<LogRecord> listView;

    public LogPanel() {

        this.listView = new ListView<>();
        listView.setCellFactory(LogCell::new);
        getChildren().add(listView);

        IMAGES = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            IMAGES.add(new Image("/be/ugent/dominion/images/player" + i + ".png"));
        }

    }

    public void add(int player, String message) {
        listView.getItems().add(new LogRecord(player, message));
        listView.scrollTo(listView.getItems().size() - 1);
    }

    public class LogCell extends ListCell<LogRecord> {

        public LogCell(ListView<LogRecord> ignored) {
        }

        @Override
        protected void updateItem(LogRecord logRecord, boolean empty) {
            super.updateItem(logRecord, empty);
            if (!empty) {
                setGraphic(new ImageView(IMAGES.get(logRecord.player() - 1)));
                setText(logRecord.message());
            }
        }
    }
}
