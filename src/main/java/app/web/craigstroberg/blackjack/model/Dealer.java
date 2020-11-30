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
    public static final String OH_NO_THE_DECK_HAS_NO_MORE_CARDS = "Oh no... The deck has no more cards!";

    private Stack<Card> dealersCards = new Stack<>();
    private Deck deck = new Deck();

    public void handOutCards(List<Player> players) {
        if (0 < players.size() && 25 < players.size()) {
            throw new BlackJack21Exception(TOO_MANY_PLAYERS);
        }
        //Dealer gets their first card
        dealersCards.add(deck.getCards().pop());
        //Each player gets their first card
        handOutCardToEachPlayer(players);
        //Dealer gets the second card
        dealersCards.add(deck.getCards().pop());
        //Each player gets their second card
        handOutCardToEachPlayer(players);
    }

    private void handOutCardToEachPlayer(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).addCard(deck.getCards().pop());
        }
    }

    public Integer getCardValue() {
        return calculateCardsValue(this.dealersCards);
    }

    public Integer calculateCardsValue(Stack<Card> cards) {
        Integer value = 0;
        Stack<Card> aces = new Stack<>();
        for (Card card : cards) {
            value += card.getValue().getHighestCardValue();
            if (Value.ACE.equals(card.getValue())) {
                aces.add(card);
            }
        }
        while (21 < value && 0 < aces.size()) {
            value -= 10;
            aces.pop();
        }
        return value;
    }

    public void addCard(Card card) {
        dealersCards.add(card);
    }

    public void handOutCard(Player player) {
        Stack<Card> cards = deck.getCards();
        if (0 < cards.size()) {
            player.addCard(cards.pop());
        }
        throw new BlackJack21Exception(OH_NO_THE_DECK_HAS_NO_MORE_CARDS);
    }
}
