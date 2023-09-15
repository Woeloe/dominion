package be.ugent.dominion.client;

import be.ugent.dominion.cards.CardSets;
import be.ugent.dominion.protocol.Event;
import be.ugent.dominion.protocol.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;

import java.util.List;
import java.util.Map;

import static be.ugent.dominion.protocol.Request.*;

/**
 * Verwerkt de gebeurtenissen die van de server komen, in de gebeurtenisverwerkende draad. Wordt
 * opgeroepen door de client met behulp van {@code Platform.runLater}
 */
public class EventProcessor {

    private final Client client;

    private final MainPanel mainPanel;
    private final LogPanel logPanel;
    private final UserArea userArea;
    private final PlayArea playArea;
    private final UIPanel uiPanel;
    private final PlayerPanel playerPanel;

    public EventProcessor(Client client) {
        this.client = client;

        // De GUI wordt hier aangemaakt en niet in de Client of in Main omdat deze klasse
        // precies de klasse is dit rechtstreeks met elke GUI-component interageert.

        this.playArea = new PlayArea();
        this.uiPanel = new UIPanel(client);
        this.userArea = new UserArea(client);

        this.logPanel = new LogPanel();

        this.playerPanel = new PlayerPanel(
                userArea, playArea, uiPanel
        );
        this.mainPanel = new MainPanel(
                logPanel,
                new BaseCards(client),
                playerPanel
        );

    }

    public void process(Event event, String[] args) {
        try {
            switch (event) {

                case SUPPLY -> supplyChanged();
                case PLAY_AREA -> playAreaChanged();
                case USER_AREA -> userAreaChanged();
                case ABC -> abcChanged();
                case GAME_STARTED -> gameStarted();
                case GAME_OVER -> gameOver();

                case ACTION_PHASE -> playActions(Integer.parseInt(args[1]));
                case BUY_PHASE -> buyCards(Integer.parseInt(args[1]));
                case CLEANUP_PHASE -> cleanupPhase(Integer.parseInt(args[1]));
                case EXEC_ACTION -> Platform.runLater(
                        () -> uiPanel.executeSpecial(args[1], Integer.parseInt(args[2]))
                );

                case PLAYS_CARD, BUYS_CARD, GAINS_CARD, TRASHES_CARD, REVEALS_CARD, DRAWS_CARDS ->
                        log(event, args[1], args[2]);

            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String send(Request request) {
        return client.getEndpoint().sendRequestToServer(request.toString());
    }

    @SuppressWarnings("SameParameterValue")
    private String send(Request request, int nr) {
        return client.getEndpoint().sendRequestToServer(request + " " + nr);
    }

    private void log(Event event, String id, String arg) {
        String name = event.name();
        log(Integer.parseInt(id), name.substring(0, name.indexOf('_')).toLowerCase() + " " + arg);
    }

    // ENKEL NAAR DEZE SPELER GESTUURD
    //////////////////////////////////

    public void userAreaChanged() throws JsonProcessingException {
        int deckSize = Integer.parseInt(send(DECK, client.getId()));
        List<String> handCards = new ObjectMapper().readerForListOf(String.class).readValue(send(HAND));
        String discardCard = send(DISCARD);
        Platform.runLater(() -> userArea.changed(deckSize, handCards, discardCard));
    }

    public void abcChanged() throws JsonProcessingException {
        String response = send(ABC);
        int[] abc = new ObjectMapper().readerFor(int[].class).readValue(response);
        Platform.runLater(() -> uiPanel.setABC(abc));
    }

    // DOOR IEDEREEN OP DEZELFDE MANIER BEHANDELD
    /////////////////////////////////////////////

    private void gameOver() throws JsonProcessingException {

        String vp = send(VICTORY_POINTS);
        int[] points = new ObjectMapper().readerFor(int[].class).readValue(vp);
        Platform.runLater(() -> uiPanel.gameOver(points));
    }

    public void supplyChanged() throws JsonProcessingException {
        String response = send(SUPPLY);
        Map<String, Integer> map = new ObjectMapper().readerForMapOf(Integer.class).readValue(response);
        Platform.runLater(() -> mainPanel.setSupplyCounts(map));
    }

    public void playAreaChanged() throws JsonProcessingException {
        String response = send(PLAY_AREA);
        List<String> cards = new ObjectMapper().readerForListOf(String.class).readValue(response);
        Platform.runLater(() -> playArea.setCards(cards));
    }

    // VERSCHIL IN BEHANDELING
    //////////////////////////

    private boolean isMe(int player) {
        return player == client.getId();
    }

    private void playActions(int player) {
        Platform.runLater(() -> {
            if (isMe(player)) {
                uiPanel.action();
            } else {
                uiPanel.pleaseWait();
            }
        });
    }

    private void buyCards(int player) {
        Platform.runLater(() -> {
            if (isMe(player)) {
                uiPanel.buy();
            } else {
                uiPanel.pleaseWait();
            }
        });
    }

    private void log(int player, String message) {
        Platform.runLater(() -> logPanel.add(player, message));
    }

    private void cleanupPhase(int id) {
        String count = send(DECK, id);
        log(id, "deck size=" + count);
    }

    public void terminated() {
        Platform.runLater(
                () -> mainPanel.getScene().getWindow().hide()
        );
    }

    public MainPanel getGUI() {
        return mainPanel;
    }

    public void gameStarted() throws JsonProcessingException {
        String[] kingdomSet = new ObjectMapper().readerFor(String[].class).readValue(send(KINGDOM));
        if (kingdomSet == null) {
            kingdomSet = CardSets.FIRST_GAME;
        }
        Kingdom kingdom = new Kingdom(kingdomSet, client);
        Platform.runLater(() -> {
            mainPanel.setKingdom(kingdom);
            playerPanel.showUIPanel();
        });
    }

}