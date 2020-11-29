package app.web.craigstroberg.blackjack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Stack;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dealer {

    public static final String TOO_MANY_PLAYERS = "There are too many players as each player will not be able to get two cards each";

    private Stack<Card> dealersCards = new Stack<>();
    private Deck deck = new Deck();

    public void handOutCards(List<Player> players) {
        if (0 < players.size() && 25 < players.size()) {
            throw new BlackJackException(TOO_MANY_PLAYERS);
        }
        //Dealer gets their first card
        dealersCards.add(deck.getCards().pop());
        //Each player gets their first card
        for (int i = 0; i < players.size(); i++) {
            players.get(i).addCard(deck.getCards().pop());
        }
        //Dealer gets the second card
        dealersCards.add(deck.getCards().pop());
        //Each player gets their second card
        for (int i = 0; i < players.size(); i++) {
            players.get(i).addCard(deck.getCards().pop());
        }
    }

    public Integer calculateCardsValue(Stack<Card> cards) {
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
