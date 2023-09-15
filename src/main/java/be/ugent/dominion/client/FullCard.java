package be.ugent.dominion.client;

import javafx.scene.image.ImageView;

/**
 * Afbeelding van een (volledige) kaart, of van de achterkant van een (anonieme) kaart.
 */
public class FullCard extends ImageView {

    public FullCard(String cardName, double factor) {
        this(ImagePair.get(cardName, factor));
    }

    private FullCard(ImagePair imagePair) {
        super(imagePair.standard());
        disabledProperty().addListener(
                o -> setImage(isDisabled() ? imagePair.grey() : imagePair.standard())
        );
    }

    public static FullCard backOfCard() {
        return new FullCard(ImagePair.get("Back", 0.7));
    }

}
