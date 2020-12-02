package app.web.craigstroberg.blackjack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.Stack;

@Data
@AllArgsConstructor
@Builder
public class Deck {

    public static final String PLEASE_ENTER_A_VALID_CARD_INDEX_0 = "Please enter a valid card index 0 -> ";
    private Stack<Card> cards = new Stack<>();

    public Deck() {
        this.create();
    }

    public void create() {
        cards.clear();
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
}
