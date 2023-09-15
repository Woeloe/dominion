package be.ugent.dominion;

import be.ugent.dominion.cards.CardSets;
import be.ugent.objprog.commhub.fx.InitialDialog;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    private static final int PORT = 7531;

    private String[] cards;

    @Override
    public void init() {
        List<String> raw = getParameters().getRaw();
        if (raw.size() == 1 && raw.get(0).equals("random")) {
            List<String> all = new ArrayList<>(Arrays.asList(CardSets.SUPPORTED));
            Collections.shuffle(all);
            cards = new String[10];
            for (int i = 0; i < 10; i++) {
                cards[i] = all.get(i);
            }
        } else {
            cards = CardSets.FIRST_GAME;
        }
    }

    @Override
    public void start(Stage stage) {
        InitialDialog initialDialog = new InitialDialog(PORT);
        initialDialog.showAndWait();

        MainDelegate delegate = new MainDelegate();
        delegate.setCards(cards); // nice to have - hoort niet bij de oorspronkelijke opgave

        if (initialDialog.getServerEndpoint() != null) {
            delegate.initClient(initialDialog.getClientEndpoint(), stage);
            delegate.initServer (initialDialog.getServerEndpoint());
        } else if (initialDialog.getClientEndpoint() != null) {
            delegate.initClient(initialDialog.getClientEndpoint(), stage);
        }
    }
}
