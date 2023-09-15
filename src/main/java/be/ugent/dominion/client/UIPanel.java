package be.ugent.dominion.client;

import be.ugent.dominion.protocol.Command;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Paneel met de statistieken, de knoppen en de status
 */
public class UIPanel extends VBox {

    private final Label label;
    private final Button button;
    private final StatisticsPanel statisticsPanel;

    public UIPanel(Client client) {

        button = new Button();
        button.setVisible(false);
        button.setOnAction(o -> client.send(Command.BUTTON));

        label = new Label();

        this.statisticsPanel = new StatisticsPanel();
        getChildren().setAll(
                label,
                button
        );
    }

    public void executeSpecial(String name, int extra) {
        switch (name) {
            case "Remodel" -> remodel(extra);
            case "Mine" -> mine(extra);
            case "Cellar" -> discard();
            case "Militia" -> militia(extra);
            case "Workshop" -> gainAny(4);

            // optioneel:
            case "Chapel" -> chapel();
            case "Moneylender" -> moneylender();
            case "Poacher" -> poacher(extra);
            case "Witch" -> witch(extra);
            case "Artisan" -> artisan(extra);
        }
    }

    public void setABC(int[] abc) {
        statisticsPanel.setABC(abc);
    }

    private void withoutABC(String style) {
        getStyleClass().setAll(style);
        if (getChildren().size() >= 3) {
            getChildren().remove(statisticsPanel);
        }
    }

    private void withABC() {
        if (getChildren().size() < 3) {
            getChildren().add(statisticsPanel);
        }
    }

    private void withoutButton(String message) {
        label.setText(message);
        button.setVisible(false);
    }

    private void withButton(String message, String caption) {
        label.setText(message);
        button.setText(caption);
        button.setVisible(true);
    }

    public void pleaseWait() { // wait() is al een methode van object...
        withoutABC("wait");
        withoutButton("Please wait ...");
    }

    public void action() {
        withABC();
        getStyleClass().clear();
        button.setText("Skip actions");
        button.setVisible(true);
        label.setText("Play an action card");
    }

    public void buy() {
        withABC();
        getStyleClass().clear();
        button.setText("Skip buys");
        button.setVisible(true);
        label.setText("Buy a card");
    }

    public void discard() {
        withABC();
        getStyleClass().clear();
        String message = "Discard some cards";
        String caption = "Finished";
        withButton(message, caption);
    }

    public void gainAny(int amount) {
        withABC();
        withoutButton("Gain a card (≤" + amount + "$)");
    }

    public void remodel(int extra) {
        if (extra == 0) {
            trashAny();
        } else {
            gainAny(extra);
        }
    }

    public void mine(int extra) {
        withABC();
        if (extra == 0) {
            withButton("Trash 1 treasure", "Skip trash");
        } else {
            withoutButton("Gain treasure (≤" + extra + "$)");
        }
    }

    public void militia(int extra) {
        if (extra == 0) {
            pleaseWait();
        } else {
            withoutABC("warning");
            label.setText("Discard until ≤ 3");
            button.setText("Use Moat");
            button.setVisible(extra != 1);
        }
    }

    public void witch(int extra) {
        if (extra == 0) {
            pleaseWait();
        } else {
            withoutABC("warning");
            withButton("Use moat or be cursed", "No moat");
        }
    }

    private void trashAny() {
        withABC();
        withoutButton("Trash 1 card");
    }

    private void chapel() {
        withABC();
        withButton("Trash ≤ 4 cards", "Stop trash");
    }

    private void poacher(int extra) {
        withABC();
        withoutButton("Trash " + extra + " card(s)");
    }

    private void moneylender() {
        withABC();
        withButton("Trash 1 copper", "Skip trash");
    }

    private void artisan(int extra) {
        withABC();
        if (extra == 0) {
            withoutButton("Gain a card (≤ 5$)");
        } else {
            withoutButton("Topdeck a card");
        }
    }

    public void gameOver(int[] points) {
        withoutABC("game-over");
        withButton("Game over!", "Show points");
        button.setOnAction(ev -> showVictoryPoints(points));
    }

    public void showVictoryPoints(int[] points) {
        new GameOverDialog(points).showAndWait();
    }

}
