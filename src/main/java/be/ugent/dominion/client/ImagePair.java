package be.ugent.dominion.client;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Paar van afbeeldingen die een kaart voorstellen. Bevat een standaard-versie en een zwart/wit-versie.
 */
public record ImagePair(Image standard, Image grey) {

    private ImagePair(Image standard) {
        this(standard, ImageUtils.convertToGrayScale(standard));
    }

    public static final Map<String, ImagePair> CACHE = new HashMap<>();

    public static final double DEFAULT_WIDTH = 156.0;
    public static final double DEFAULT_HEIGHT = 250.0;


    /**
     * Geef het paar afbeeldingen terug voor het gegeven kaarttype. Slaat de afbeeldingen op zodat ze niet
     * opnieuw moeten worden ingeladen en bewerkt wanneer ze een tweede keer worden opgevraagd met dezelfde
     * parameters.
     */
    public static ImagePair get(String Card, double scale) {
        return CACHE.computeIfAbsent(Card + "-" + scale,
                key -> new ImagePair(
                        new Image("be/ugent/dominion/images/" + Card + ".jpg",
                                (int) (DEFAULT_WIDTH * scale),
                                (int) (DEFAULT_HEIGHT * scale),
                                true, true)
                )
        );
    }

}
