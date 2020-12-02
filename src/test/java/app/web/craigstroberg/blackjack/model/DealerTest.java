package app.web.craigstroberg.blackjack.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DealerTest {

    public static final String IT_IS_A_DRAW = "It is a draw!";
    public static final String PLAYER_WON_MESSAGE = " you won against the dealer! Winner Winner Chicken Dinner :) ";
    public static final String DEALER_WINS_THIS_ROUND = "Dealer wins this round!";
    public static final String OH_NO_YOUR_CARDS_VALUES_ARE_BIGGER_THAN_21 = "Oh no your cards values are bigger than 21";

    @Test
    public void handOutCards() {
        Dealer dealer = new Dealer();
        List<Player> players = new ArrayList<>();
        players.add(Player.builder().build());
        dealer.handOutCards(players);
        assertNotNull(dealer);
        assertEquals(2, players.get(0).getCards().size());
        assertEquals(2, dealer.getDealersCards().size());
    }

    @Test
    public void calculateDealersValue() {
        Dealer dealer = getDealerWithCards();
        assertEquals(19, dealer.getCardValue());
    }

    @Test
    public void calculateCardsValueWhenAPlayerHasJackAndANine() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.JACK).build());
        cards.add(Card.builder().suit(Suit.HEARTS).value(Value.NINE).build());
        Player player = Player.builder()
                .cards(cards)
                .name("Dealer")
                .build();
        Integer actual = getDealerWithCards()
                .calculateCardsValue(player.getCards());
        assertNotNull(actual);
        assertEquals(19, actual);

    }

    @Test
    public void calculateCardsValueWhenAPlayerHasTwoAcesAndASeven() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.ACE).build());
        cards.add(Card.builder().suit(Suit.HEARTS).value(Value.SEVEN).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.ACE).build());
        Player player = Player.builder()
                .cards(cards)
                .name("Lemmy")
                .build();
        Dealer dealerWithCards = getDealerWithCards();
        Integer actual = dealerWithCards.calculateCardsValue(player.getCards());
        assertNotNull(actual);
        assertEquals(19, actual);
        String winner = dealerWithCards.getWinner(player);
        System.out.println(winner);
        assertEquals(IT_IS_A_DRAW, winner);
    }

    @Test
    public void calculateCardsValueWhenAPlayerHasTwoFoursAndAKing() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.KING).build());
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.FOUR).build());
        cards.add(Card.builder().suit(Suit.CLUBS).value(Value.FOUR).build());
        Player player = Player.builder()
                .cards(cards)
                .name("Andrew")
                .build();
        Dealer dealerWithCards = getDealerWithCards();
        Integer actual = dealerWithCards.calculateCardsValue(player.getCards());
        assertNotNull(actual);
        assertEquals(18, actual);
        String winner = dealerWithCards.getWinner(player);
        System.out.println(winner);
        assertEquals(DEALER_WINS_THIS_ROUND, winner);
    }

    @Test
    public void calculateCardsValueWhenAPlayerHasThreeTwosAndAFourAndAFive() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.TWO).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.TWO).build());
        cards.add(Card.builder().suit(Suit.HEARTS).value(Value.TWO).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.FOUR).build());
        cards.add(Card.builder().suit(Suit.CLUBS).value(Value.FIVE).build());
        Player player = Player.builder()
                .cards(cards)
                .name("Billy")
                .build();
        Dealer dealerWithCards = getDealerWithCards();
        Integer actual = dealerWithCards.calculateCardsValue(player.getCards());
        assertNotNull(actual);
        assertEquals(15, actual);
        String winner = dealerWithCards.getWinner(player);
        System.out.println(winner);
        assertEquals(player.getName() + PLAYER_WON_MESSAGE, winner);
    }

    @Test
    public void calculateCardsValueWhenAPlayerHasAQueenAndASixAndANine() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.QUEEN).build());
        cards.add(Card.builder().suit(Suit.CLUBS).value(Value.SIX).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.NINE).build());
        Player player = Player.builder()
                .cards(cards)
                .name("Carla")
                .build();
        Dealer dealerWithCards = getDealerWithCards();
        Integer actual = dealerWithCards.calculateCardsValue(player.getCards());
        assertNotNull(actual);
        assertEquals(25, actual);
        String winner = dealerWithCards.getWinner(player);
        System.out.println(winner);
        assertEquals(OH_NO_YOUR_CARDS_VALUES_ARE_BIGGER_THAN_21, winner);
    }

    @Test
    void playerShouldWinAsTheir5CardsAreLessThan21() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.TWO).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.TWO).build());
        cards.add(Card.builder().suit(Suit.HEARTS).value(Value.TWO).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.FOUR).build());
        cards.add(Card.builder().suit(Suit.CLUBS).value(Value.FIVE).build());
        Player player = Player.builder()
                .cards(cards)
                .name("Billy")
                .build();
        Dealer dealerWithCards = getDealerWithCards();
        Integer actual = dealerWithCards
                .calculateCardsValue(player.getCards());
        assertNotNull(actual);
        assertEquals(15, actual);
        String winner = dealerWithCards.getWinner(player);
        System.out.println(winner);
        assertEquals(player.getName() + PLAYER_WON_MESSAGE, winner);
    }

    private Dealer getDealerWithCards() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.builder().value(Value.JACK).suit(Suit.SPADES).build());
        dealer.addCard(Card.builder().value(Value.NINE).suit(Suit.HEARTS).build());
        return dealer;
    }
}