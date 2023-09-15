package be.ugent.dominion.server;

import be.ugent.dominion.cards.Card;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class CardListModel  {

    protected List<Card> contents;

    public CardListModel() {
        contents = new ArrayList<>();
    }

    public List<Card> getContents() {
        return contents;
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(contents.stream()
                .map(el -> el == null ? null : el.getName())
                .toList()
        );
    }

    public void takeAllFrom(CardListModel other) {
        for (Card content : other.contents) {
            if (content != null) {
                contents.add(content);
            }
        }
        other.contents.clear();
    }

}
