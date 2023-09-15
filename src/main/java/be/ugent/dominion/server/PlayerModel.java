package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import be.ugent.dominion.protocol.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerModel {

    private final DeckModel deck;
    private final HandModel hand;
    private final DiscardModel discard;
    private final CardListModel playArea;
    private final SupplyModel supply;
    private final Server server;

    private final int id;

    private final int[] abc;

    private int firstSilverCoins;

    public PlayerModel(Server server, int id, CardListModel playAreaModel) {
        this.server = server;
        this.id = id;
        this.deck = new DeckModel();
        this.hand = new HandModel();
        this.discard = new DiscardModel();
        this.playArea = playAreaModel;
        this.supply = server.getSupply();
        this.abc = new int[]{0, 0, 0};
    }

    public DeckModel getDeck() {
        return deck;
    }

    public DiscardModel getDiscard() {
        return discard;
    }

    public HandModel getHand() {
        return hand;
    }

    public CardListModel getPlayArea() {
        return playArea;
    }

    public int getId() {
        return id;
    }

    public void sendLogEvent(Event event, String arg) {
        server.sendToAll(event, getId() + " " + arg);
    }

   public void startGame(SupplyModel supply) {
        deck.startGame(supply);
        deck.drawHand(hand, discard, 5);
    }

    public String abcToJSON() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(abc);
    }

    public Card getCardFromHand(int index) {
        return hand.getContents().get(index);
    }

    public void playCard(Card card, int index) {
        hand.getContents().set(index, null);
        playArea.getContents().add(card);
        sendLogEvent(Event.PLAYS_CARD, card.getName());
        card.play(this);
    }

    public Card trash(int index) {
        Card card = hand.getContents().remove(index);
        sendLogEvent(Event.TRASHES_CARD, card.getName());
        return card;
    }

    public void endOneAction() {
        abc[0]--;
    }

    public void addFirstSilverCoin() {
        // effect van Merchant
        firstSilverCoins++;
    }

    public int getFirstSilverCoins() {
        int result = firstSilverCoins;
        firstSilverCoins = 0;
        return result;
    }

    public boolean isValidBuy(Card card) {
        return abc[1] > 0 && abc[2] >= card.getPrice();
    }

    public void buy(Card card) {
        if (abc[1] > 0) {
            int newCoins = abc[2] - card.getPrice();
            if (newCoins >= 0) {
                abc[2] = newCoins;
                discard.contents.add(supply.take(card.getName()));
                abc[1]--;
                sendLogEvent(Event.BUYS_CARD, card.getName());
            }
        }
    }

    public void clearActions() {
        abc[0] = 0;
    }

    public void clearBuys() {
        abc[1] = 0;
    }

    public void gain(String name) {
        discard.contents.add(supply.take(name));
        sendLogEvent(Event.GAINS_CARD, name);
    }

    public void gainToHand(String name) {
        hand.contents.add(supply.take(name));
        sendLogEvent(Event.GAINS_CARD, name);
    }

    public boolean noBuysLeft() {
        return abc[1] <= 0;
    }

    public boolean noActionsLeft() {
        return abc[0] <= 0;
    }

    public void startTurn() {
        abc[0] = 1;
        abc[1] = 1;
        abc[2] = 0;
        firstSilverCoins = 0;
    }

    public void addActions(int nr) {
        abc[0] += nr;
    }

    public void addBuys(int nr) {
        abc[1] += nr;
    }

    public void addCoins(int nr) {
        abc[2] += nr;
    }

    public void draw(int extraCards) {
        if (extraCards > 0) {
            deck.drawHand(hand, discard, extraCards);
            sendLogEvent(Event.DRAWS_CARDS, "" + extraCards);
        }
    }

    public void cleanup() {
        abc[2] = 0; // actions en buys zouden al op 0 moeten staan
        discard.takeAllFrom(hand);
        discard.takeAllFrom(playArea);
        draw(5);

        server.sendToAll(Event.CLEANUP_PHASE, getId());
        server.send(Event.USER_AREA);
    }

    public void discardFromHand(int index) {
        Card card = hand.getContents().get(index);
        hand.getContents().set(index, null);
        discard.getContents().add(card);
    }

public void topDeck(int index) {
        Card card = hand.getContents().get(index);
        hand.getContents().set(index, null);
        deck.getContents().add(card);
    }


    public int getVictoryPoints() {
        List<Card> list = new ArrayList<>(deck.getContents());
        list.addAll(hand.getContents());
        list.addAll(discard.getContents());
        int size = list.size();
        return list.stream().filter(Objects::nonNull).mapToInt(t -> t.getVictoryPoints(size)).sum();
    }
}
