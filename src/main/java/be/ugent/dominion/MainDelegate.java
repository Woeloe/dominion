package be.ugent.dominion;

import be.ugent.dominion.client.Client;
import be.ugent.dominion.server.Server;
import be.ugent.objprog.commhub.ClientEndpoint;
import be.ugent.objprog.commhub.ServerEndpoint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static be.ugent.dominion.protocol.Command.TERMINATE;

public class MainDelegate {

    private String[] cards;

    public void setCards(String[] cards) {
        this.cards = cards;
    }

    /**
     * Start het clientgedeelte van de toepassing en de GUI. De server zal de client op de hoogte brengen
     * wanneer het spel kan starten.
     */
    public void initClient(ClientEndpoint clientEndpoint, Stage stage) {
        Client client = new Client(clientEndpoint);
        Parent gui = client.getGUI();
//        gui = new ScaledPane(0.8, gui);
        Scene scene = new Scene(gui);
        stage.setScene(scene);
        stage.setTitle("Dominion [" + client.getId() +
                "] - Objectgericht programmeren 2022-2023 © K. Coolsaet");

        stage.setOnCloseRequest(o -> client.send(TERMINATE));
        stage.show();
    }

    /**
     * Start het servergedeelte van de toepassing en breng de clients op de hoogte
     * dat het spel is gestart.
     * <p>
     * Wordt pas opgeroepen nadat een eventuele (primaire) client is opgestart op hetzelfde toestel.
     */
    public void initServer(ServerEndpoint serverEndpoint) {
        new Server(serverEndpoint).startGame(cards);
    }

}
