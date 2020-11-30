package app.web.craigstroberg.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class BlackJack21 {

    public static final String TOO_MANY_PLAYERS = "There are too many players as each player will not be able to get two cards each";
    private Dealer dealer;
    private List<Player> players;

    private void play(int numberOfPlayers) {
        if (25 < numberOfPlayers) {
            throw new BlackJack21Exception(TOO_MANY_PLAYERS);
        }
        players = new ArrayList<>(numberOfPlayers);

    }
}
