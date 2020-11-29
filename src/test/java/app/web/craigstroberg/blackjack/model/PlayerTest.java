package app.web.craigstroberg.blackjack.model;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerTest {

    @Test
    void calculateCardsValueWhenAPlayerHasJackAndANine() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.JACK).build());
        cards.add(Card.builder().suit(Suit.HEARTS).value(Value.NINE).build());
        Player player = Player.builder()
                .cards(cards)
                .playerType(PlayerType.DEALER)
                .build();
        Integer actual = player.calculateCardsValue();
        assertNotNull(actual);
        assertEquals(19, actual);
    }

    @Test
    void calculateCardsValueWhenAPlayerHasTwoAcesAndASeven() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.ACE).build());
        cards.add(Card.builder().suit(Suit.HEARTS).value(Value.SEVEN).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.ACE).build());
        Player player = Player.builder()
                .cards(cards)
                .playerType(PlayerType.PLAYER)
                .build();
        Integer actual = player.calculateCardsValue();
        assertNotNull(actual);
        assertEquals(19, actual);
    }

    @Test
    void calculateCardsValueWhenAPlayerHasTwoFoursAndAKing() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.KING).build());
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.FOUR).build());
        cards.add(Card.builder().suit(Suit.CLUBS).value(Value.FOUR).build());
        Player player = Player.builder()
                .cards(cards)
                .playerType(PlayerType.PLAYER)
                .build();
        Integer actual = player.calculateCardsValue();
        assertNotNull(actual);
        assertEquals(18, actual);
    }

    @Test
    void calculateCardsValueWhenAPlayerHasThreeTwosAndAFourAndAFive() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.TWO).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.TWO).build());
        cards.add(Card.builder().suit(Suit.HEARTS).value(Value.TWO).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.FOUR).build());
        cards.add(Card.builder().suit(Suit.CLUBS).value(Value.FIVE).build());
        Player player = Player.builder()
                .cards(cards)
                .playerType(PlayerType.PLAYER)
                .build();
        Integer actual = player.calculateCardsValue();
        assertNotNull(actual);
        assertEquals(15, actual);
    }

    @Test
    void calculateCardsValueWhenAPlayerHasAQueenAndASixAndANine() {
        Stack<Card> cards = new Stack<>();
        cards.add(Card.builder().suit(Suit.SPADES).value(Value.QUEEN).build());
        cards.add(Card.builder().suit(Suit.CLUBS).value(Value.SIX).build());
        cards.add(Card.builder().suit(Suit.DIAMONDS).value(Value.NINE).build());
        Player player = Player.builder()
                .cards(cards)
                .playerType(PlayerType.PLAYER)
                .build();
        Integer actual = player.calculateCardsValue();
        assertNotNull(actual);
        assertEquals(25, actual);
    }
}