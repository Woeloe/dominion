package be.ugent.dominion.client;

import be.ugent.dominion.util.CardsLayout;

import java.util.List;

/**
 * Paneel met de kaarten die je aan het uitspelen bent
 */
public class PlayArea extends CardsLayout {

    public PlayArea() {
        super(ImagePair.DEFAULT_HEIGHT * 0.7, ImagePair.DEFAULT_WIDTH * 0.7,
                0.15, 0.2, 3);
        setPrefHeight(USE_COMPUTED_SIZE);
    }

    public void setCards(List<String> cards) {
        getChildren().setAll(
                cards.stream().map(card -> new FullCard(card, 0.7)).toList()
        );
    }

}
