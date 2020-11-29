package app.web.craigstroberg.blackjack.model;

import lombok.Data;

import java.util.Collections;
import java.util.Stack;

@Data
public class Deck {

    public static final String PLEASE_ENTER_A_VALID_CARD_INDEX_0 = "Please enter a valid card index 0 -> ";
    private Stack<Card> cards;

    public Deck() {
        this.create();
    }

    public void create() {
        cards = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(Card.builder()
                        .suit(suit)
                        .value(value)
                        .build());
            }
        }
        Collections.shuffle(cards);
    }

    public Card popCard() {
        return cards.pop();
    }
}
