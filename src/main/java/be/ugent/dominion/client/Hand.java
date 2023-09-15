package be.ugent.dominion.client;

import be.ugent.dominion.protocol.Command;
import be.ugent.dominion.util.CardsLayout;
import javafx.scene.input.MouseEvent;

import java.util.List;

/**
 * Paneel met de kaarten die je in de hand houdt.
 */
public class Hand extends CardsLayout {

    private final Client client;

    public Hand(Client client) {
        super(ImagePair.DEFAULT_HEIGHT, ImagePair.DEFAULT_WIDTH , 0.0, 0.0, 1);
        setPrefHeight(USE_COMPUTED_SIZE);
        this.client = client;
        setOnMouseClicked(this::mouseClicked);
    }

    public void setCards(List<String> cards) {
        getChildren().setAll(
                cards.stream().map(card -> card == null ? new GhostCard() : new FullCard(card, 1.0)).toList()
        );
    }

    private void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getTarget() instanceof FullCard fullCard) {
            mouseEvent.consume();
            int index = getChildren().indexOf(fullCard);
            if (index >= 0) {
                client.send(Command.HAND, index);
            } else {
                throw new IllegalStateException("Mouse clicked target not found among children");
            }
        }
    }
}
