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

    private PlayerType playerType;
    private Stack<Card> cards;

    public Integer calculateCardsValue() {
        Integer value = 0;
        Stack<Card> aces = new Stack<>();
        for (Card card : cards) {
            value += card.getValue().getHighestCardValue();
            if (Suit.HEARTS.equals(card.getSuit())) {
                aces.add(card);
            }
        }
        while (21 < value && 0 < aces.size()) {
            value -= 10;
            aces.pop();
        }
        return value;
    }
}
