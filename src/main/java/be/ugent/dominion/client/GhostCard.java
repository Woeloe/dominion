package be.ugent.dominion.client;

import javafx.scene.layout.Pane;

/**
 * Neemt de plaats in van een kaart (in de hand) maar is volledig transparant.
 */
public class GhostCard extends Pane {

    public GhostCard () {
        setPrefWidth(ImagePair.DEFAULT_WIDTH);
        setPrefHeight(ImagePair.DEFAULT_HEIGHT);
        setMouseTransparent(true);
    }

}
