package app.web.craigstroberg.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class BlackJack21 {

    private Deck deck;
    private List<Player> players;

    private void play(int numberOfPlayers) {
        deck = new Deck();
        players = new ArrayList<>(numberOfPlayers);
    }


}
