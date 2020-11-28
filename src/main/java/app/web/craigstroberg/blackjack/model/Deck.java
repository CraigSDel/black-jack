package app.web.craigstroberg.blackjack.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Deck {

    public static final String PLEASE_ENTER_A_VALID_CARD_INDEX_0 = "Please enter a valid card index 0 -> ";
    private List<Card> cards;

    public void create() {
        cards = new ArrayList<>();
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

    public void removeCard(int i) {
        cards.remove(i);
    }

    public Card getCard(int i) {
        if (0 <= i && i <= cards.size()) {
            return cards.get(i);
        }
        throw new BlackJackException(PLEASE_ENTER_A_VALID_CARD_INDEX_0 + cards.size());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void draw(Deck deck) {
        cards.add(deck.getCard(0));
        deck.removeCard(0);
    }
}
