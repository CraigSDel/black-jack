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

    private Stack<Card> cards;
    private String name;

    public void addCard(Card card) {
        if (cards == null) {
            cards = new Stack<>();
        }
        cards.add(card);
    }
}
