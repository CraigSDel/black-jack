package app.web.craigstroberg.blackjack.service;

import app.web.craigstroberg.blackjack.model.Card;
import app.web.craigstroberg.blackjack.model.Deck;
import app.web.craigstroberg.blackjack.model.Suit;
import app.web.craigstroberg.blackjack.model.Value;
import app.web.craigstroberg.blackjack.service.impl.BlackJackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlackJackServiceTest {

    @InjectMocks
    private BlackJackServiceImpl blackJackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        Deck deck = blackJackService.create();
        assertNotNull(deck);
        assertEquals(Card.builder()
                .suit(Suit.ACE)
                .value(Value.ACE).build(), deck.getCards().get(0));
    }
}