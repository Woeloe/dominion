package be.ugent.dominion.client;

import javafx.scene.layout.StackPane;

public class DiscardPile extends StackPane {
// stackPane met één kind

    public DiscardPile() {
        setCard("none");
    }

    public void setCard(String card) {
        FullCard fullCard;
        if (card.equals("none")) {
            fullCard = FullCard.backOfCard();
            setDisable(true);
        } else {
            fullCard = new FullCard(card, 0.7);
            setDisable(false);
        }
        getChildren().setAll(fullCard);
    }

}
