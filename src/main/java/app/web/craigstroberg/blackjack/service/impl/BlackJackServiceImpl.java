package app.web.craigstroberg.blackjack.service.impl;

import app.web.craigstroberg.blackjack.model.Deck;
import app.web.craigstroberg.blackjack.service.BlackJackService;
import org.springframework.stereotype.Service;

@Service
public class BlackJackServiceImpl implements BlackJackService {

    @Override
    public Deck create() {
        Deck deck = new Deck();
        deck.create();
        return deck;
    }
}
