package app.web.craigstroberg.blackjack.service.impl;

import app.web.craigstroberg.blackjack.model.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BlackJackServiceImplTest {

    @InjectMocks
    private BlackJackServiceImpl blackJackService;

    @BeforeEach
    public void setMockOutput() {
    }


    @Test
    public void create() {
        Deck deck = blackJackService.create();
        assertNotNull(deck);
    }
}