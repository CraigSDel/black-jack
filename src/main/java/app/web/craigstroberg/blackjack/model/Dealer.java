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

    public static final String HERE_ARE_YOUR_CURRENT_CARDS = "Dealer here are your current cards.";
    public static final String TOO_MANY_PLAYERS = "There are too many players as each player will not be able to get two cards each";
    public static final String OH_NO_THE_DECK_HAS_NO_MORE_CARDS = "Oh no... The deck has no more cards!";
    public static final String PLAYER_WON_MESSAGE = " you won against the dealer! Winner Winner Chicken Dinner :) ";
    public static final String IT_IS_A_DRAW = "It is a draw!";
    public static final String VALUES_ARE_BIGGER_THAN_21 = "Oh no your cards values are bigger than 21";
    public static final String DEALER_WINS_THIS_ROUND = "Dealer wins this round!";
    public static final String DEALERS_HAND_EQUALS = "Dealers hand equals ";
    public static final String PLAYERS_HAND_EQUALS = "Players hand equals ";
    public static final String NEW_LINE = "\n";
    public static final int TWENTY_ONE = 21;
    public static final String THERE_IS_NO_WINNER = "There is no winner";
    public static final String THE_DEALER_IS_OUT_OF_THIS_ROUND = "The dealer is out of this round.";

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
        while (TWENTY_ONE < value && 0 < aces.size()) {
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
        } else {
            throw new BlackJack21Exception(OH_NO_THE_DECK_HAS_NO_MORE_CARDS);
        }
    }

    public void printCards() {
        System.out.println(HERE_ARE_YOUR_CURRENT_CARDS);
        for (Card card : dealersCards) {
            System.out.println(card.toString());
        }
    }

    public void printTopCard() {
        System.out.println(dealersCards.peek());
    }

    public String getWinner(Player player) {
        Integer playerCardValue = this.calculateCardsValue(player.getCards());
        Integer dealersCardValue = this.getCardValue();
        System.out.println(NEW_LINE + DEALERS_HAND_EQUALS + dealersCardValue
                + NEW_LINE + PLAYERS_HAND_EQUALS + playerCardValue);

        if (player.getCards().size() >= 5 & playerCardValue < TWENTY_ONE) {
            return player.getName() + PLAYER_WON_MESSAGE;
        }

        if (dealersCardValue > TWENTY_ONE) {
            return THE_DEALER_IS_OUT_OF_THIS_ROUND;
        }

        if (playerCardValue > TWENTY_ONE) {
            return VALUES_ARE_BIGGER_THAN_21;
        }

        if (dealersCardValue < playerCardValue) {
            return player.getName() + PLAYER_WON_MESSAGE;
        }

        if (dealersCardValue > playerCardValue) {
            return DEALER_WINS_THIS_ROUND;
        }

        if (dealersCardValue == playerCardValue) {
            return IT_IS_A_DRAW;
        }

        return THERE_IS_NO_WINNER;
    }
}
