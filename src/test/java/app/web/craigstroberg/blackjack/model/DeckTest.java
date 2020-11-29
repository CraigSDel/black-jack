package app.web.craigstroberg.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeckTest {
    @Test
    public void createADeckOfCards() {
        Deck deck = new Deck();
        deck.create();
        assertNotNull(deck);
    }
}