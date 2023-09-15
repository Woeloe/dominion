package be.ugent.dominion.client;

import be.ugent.dominion.cards.Card;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Toont een 'halve' kaart, met titel, typenaam, prijs en aantal
 */
public class HalfCard extends AnchorPane {

    private final Card card;

    public HalfCard(Card card, double factor) {
        this(
                card,
                ImagePair.get(card.getName(), factor),
                card.getName(),
                card.getFooter(),
                card.getPrice(),
                card.getStyle()
        );
    }

    public Card getCard() {
        return card;
    }

    private HalfCard(Card card, ImagePair imagePair, String header, String footer, int price, String style) {

        this.card = card;

        getStyleClass().add(style);

        Image image = imagePair.standard();
        ImageView imageView = new ImageView(image);
        double imageHeight = image.getHeight();
        double imageWidth = image.getWidth();
        imageView.setViewport(new Rectangle2D(
                0.0, imageHeight * 0.11,
                imageWidth, imageHeight * 0.40
        ));
        setTopAnchor(imageView, imageHeight * 0.11);
        setPrefHeight(imageHeight * 0.62);

        Label headerLabel = new Label(header);
        headerLabel.getStyleClass().add("header");
        setTopAnchor(headerLabel, 0.0);
        setLeftAnchor(headerLabel, 0.0);
        setRightAnchor(headerLabel, 0.0);

        Label priceLabel = new Label("" + price);
        priceLabel.getStyleClass().add("price");
        setBottomAnchor(priceLabel, -4.0);
        setLeftAnchor(priceLabel, -4.0);

        Label footerLabel = new Label(footer);
        footerLabel.getStyleClass().add("footer");
        setBottomAnchor(footerLabel, 0.0);
        setLeftAnchor(footerLabel, 0.0);
        setRightAnchor(footerLabel, 0.0);

        getChildren().addAll(headerLabel, imageView, footerLabel, priceLabel);

        disabledProperty().addListener(
                o -> imageView.setImage(isDisabled() ? imagePair.grey() : image)
        );

    }
}
