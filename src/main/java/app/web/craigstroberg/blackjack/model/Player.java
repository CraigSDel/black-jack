package app.web.craigstroberg.blackjack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Stack;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {

    public static final String HERE_ARE_YOUR_CURRENT_CARDS = " here are your current cards.";
    private Stack<Card> cards;
    private String name;

    public void addCard(Card card) {
        if (cards == null) {
            cards = new Stack<>();
        }
        cards.add(card);
    }

    public void printCards() {
        System.out.println(name + HERE_ARE_YOUR_CURRENT_CARDS);
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }
}
