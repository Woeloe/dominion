package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.cards.Cards;
import be.ugent.dominion.protocol.Event;

public class ArtisanState extends State {

    public ArtisanState(Server server) {
        super(server);
    }

    @Override
    public void initState(PlayerModel currentPlayer) {
        server.sendExecAction(currentPlayer, "Artisan", 0);
    }

    @Override
    protected void supplyClicked(String cardName) {
        Card card = Cards.get(cardName);
        if (card.getPrice() <= 5) {
            PlayerModel player = server.currentPlayer();
            player.gainToHand(card.getName());
            server.sendToAll(Event.SUPPLY);
            server.send(Event.USER_AREA);
            server.setState(new ArtisanStateX(server));
        }
    }

}
