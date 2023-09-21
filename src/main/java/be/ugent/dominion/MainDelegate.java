package be.ugent.dominion;

//import be.ugent.dominion.clientSide.Game;
//import be.ugent.dominion.serverSide.Server;
import be.ugent.objprog.commhub.ClientEndpoint;
import be.ugent.objprog.commhub.ServerEndpoint;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainDelegate {

    /**
     * Start het clientgedeelte van de toepassing en de GUI. De server zal de client op de hoogte brengen
     * wanneer het spel kan starten.
     */
    public void initClient(ClientEndpoint clientEndpoint, Stage stage){
        stage.setScene(new Scene(new MainPanel()));
        stage.show();
    }

    /**
     * Start het servergedeelte van de toepassing en breng de clients op de hoogte
     * dat het spel is gestart.
     *
     * Wordt pas opgeroepen nadat een eventuele (primaire) client is opgestart op hetzelfde toestel.
     */
    public void initServer(ServerEndpoint serverEndpoint) {

    }

}
