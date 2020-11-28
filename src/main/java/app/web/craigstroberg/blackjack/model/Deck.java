package app.web.craigstroberg.blackjack.model;

import lombok.Data;

import java.util.List;

@Data
public class Deck {

    private List<Card> cards;

    public void create() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(Card.builder()
                        .suit(suit)
                        .value(value)
                        .build());
            }
        }
    }
}
