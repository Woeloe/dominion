package be.ugent.dominion.server;

import be.ugent.dominion.cards.CardSets;
import be.ugent.dominion.protocol.Command;
import be.ugent.dominion.protocol.Event;
import be.ugent.dominion.protocol.Request;
import be.ugent.objprog.commhub.ClientCommunicationHandler;
import be.ugent.objprog.commhub.ServerEndpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static be.ugent.dominion.protocol.Event.*;

public class Server implements ClientCommunicationHandler {

    private final SupplyModel supply;
    private final List<PlayerModel> playerModels;
    private State state;
    private final ServerEndpoint endpoint;
    private int currentId;
    private String[] kingdomSet;

    private final int nrOfPlayers;

    public Server(ServerEndpoint endpoint) {
        this.endpoint = endpoint;
        endpoint.setHandler(this);
        this.nrOfPlayers = endpoint.getNumberOfClients();
        this.supply = new SupplyModel();
        this.playerModels = new ArrayList<>();
        this.state = null;

        // Het spelgebied wordt gedeeld door alle spelers. Misschien is dit geen goed idee...
        CardListModel playAreaModel = new CardListModel();
        for (int i = 0; i < nrOfPlayers; i++) {
            playerModels.add(new PlayerModel(this, i + 1, playAreaModel));
        }
        this.currentId = 1; // speler 1 begint
    }

    public SupplyModel getSupply() {
        return supply;
    }

    public void startGame(String[] kingdomSet) {
        this.kingdomSet = kingdomSet;
        supply.addTypes(CardSets.BASE_CARDS, nrOfPlayers);
        supply.addTypes(kingdomSet, nrOfPlayers);
        for (PlayerModel playerModel : playerModels) {
            playerModel.startGame(supply);
        }
        sendToAll(GAME_STARTED);
        sendToAll(SUPPLY);
        sendToAll(USER_AREA);
        sendToAll(ABC);
        startTurn();
    }

    public boolean isGameOver() {
        return supply.isGameOver();
    }

    public String victoryPointsAsJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                playerModels.stream().map(PlayerModel::getVictoryPoints).toList()
        );
    }

    public String kingdomSetAsJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(kingdomSet);
    }


    public List<PlayerModel> getPlayerModels() {
        return playerModels;
    }

    /**
     * Stuur een aantal gebeurtenissen naar de speler die aan zet is
     */
    public void send(Event... events) {
        for (Event event : events) {
            endpoint.sendToClient(currentId, event.toString());
        }
    }

    public void send(PlayerModel player, Event event) {
        endpoint.sendToClient(player.getId(), event.toString());
    }

    public void sendToAll(Event event) {
        endpoint.sendToAllClients(event.toString());
    }

    public void sendToAll (Event event, String arg) {
        endpoint.sendToAllClients(event + " " + arg);
    }

    public void sendExecAction(PlayerModel destination, String name, int phase) {
        endpoint.sendToClient(
                destination.getId(),
                EXEC_ACTION + " " + name + " " + phase
        );
    }

    public void sendToAll(Event event, int arg) {
        endpoint.sendToAllClients(event + " " + arg);
    }

    @Override
    public void processClientCommand(int i, String s) {
//        System.out.println("Server processing " + i + " " + s);
        String[] args = s.split(" ");
        PlayerModel player = getPlayer(i);
        switch (Command.valueOf(args[0])) {
            case HAND -> state.handClicked(player, Integer.parseInt(args[1]));
            case SUPPLY -> state.supplyClicked(player, args[1]);
            case BUTTON -> state.buttonPressed(player);
            case TERMINATE -> endpoint.terminate();
            default -> System.err.println("Onbekende opdracht van client " + i + ": " + s);
        }
    }

    @Override
    public String processClientRequest(int i, String s) {
        String[] args = s.split(" ");
        try {
            PlayerModel playerModel = getPlayer(i);
            return switch (Request.valueOf(args[0])) {
                case SUPPLY -> supply.toJson();
                case DECK -> getPlayer(Integer.parseInt(args[1])).getDeck().getCountAsString();
                case HAND -> playerModel.getHand().toJson();
                case DISCARD -> playerModel.getDiscard().topCard();
                case ABC -> playerModel.abcToJSON();
                case PLAY_AREA -> playerModel.getPlayArea().toJson();
                case VICTORY_POINTS -> victoryPointsAsJson();
                case KINGDOM -> kingdomSetAsJson();
            };
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalArgumentException ex) {
            System.err.println("Onbekend bericht van client " + i + ": " + s);
            return "";
        }
    }

    private PlayerModel getPlayer(int i) {
        return playerModels.get(i - 1);
    }

    @Override
    public void clientJoined(int i) {

    }

    public PlayerModel currentPlayer() {
        return getPlayer(currentId);
    }

    public void setState(State state) {
        this.state = state;
        state.initState(currentPlayer());
    }

    public void nextPlayer() {
        if (currentId == nrOfPlayers) {
            currentId = 1;
        } else {
            currentId++;
        }
        startTurn();
    }

    private void startTurn() {
        currentPlayer().startTurn();
        send(ABC);
        setState(new ActionState(this));
    }

    public void endSpecialAction() {
        currentPlayer().endOneAction();
        send(ABC);
        setState(new ActionState(this));
    }

    public int nrOfEmptySupplyPiles() {
        return supply.nrOfEmptyPiles();
    }
}
