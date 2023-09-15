package be.ugent.dominion.client;

import be.ugent.dominion.protocol.Command;
import be.ugent.dominion.protocol.Event;
import be.ugent.objprog.commhub.ClientEndpoint;
import be.ugent.objprog.commhub.ServerCommunicationListener;

public class Client implements ServerCommunicationListener {

    private final EventProcessor eventProcessor;
    private final ClientEndpoint endpoint;

    public Client(ClientEndpoint endpoint) {
        this.endpoint = endpoint;
        this.eventProcessor = new EventProcessor(this); // maakt ook de GUI aan
        endpoint.setListener(this);
    }

    public ClientEndpoint getEndpoint() {
        return endpoint;
    }

    public void send(Command command) {
        endpoint.sendCommandToServer(command.toString());
    }

    public void send(Command command, Object argument) {
        endpoint.sendCommandToServer(command + " " + argument);
    }

    @Override
    public void processServerMessage(String s) {
        try {
            String[] args = s.split(" ");
            eventProcessor.process(Event.valueOf(args[0]), args);
        } catch (IllegalArgumentException ex) {
            System.err.println("Onbekend bericht van server: " + s);
        }
    }

    public int getId() {
        return endpoint.getId();
    }

    @Override
    public void terminated() {
        eventProcessor.terminated();
    }

    public MainPanel getGUI() {
        return eventProcessor.getGUI();
    }
}
