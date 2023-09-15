package be.ugent.dominion;

import be.ugent.objprog.commhub.fx.InitialDialog;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private final static int PORT = 7531;

    @Override
    public void start(Stage stage) {
        InitialDialog initialDialog = new InitialDialog(PORT);
        initialDialog.showAndWait();

        MainDelegate delegate = new MainDelegate();

        if (initialDialog.getServerEndpoint() != null) {
            delegate.initClient(initialDialog.getClientEndpoint(), stage);
            delegate.initServer (initialDialog.getServerEndpoint());
        } else if (initialDialog.getClientEndpoint() != null) {
            delegate.initClient(initialDialog.getClientEndpoint(), stage);
        }
    }
}
