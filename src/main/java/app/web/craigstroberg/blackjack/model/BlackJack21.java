package app.web.craigstroberg.blackjack.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack21 {

    public static final String TOO_MANY_PLAYERS = "There are too many players as each player will not be able to get two cards each";
    public static final String PATH_TO_RULES_FILE = "src\\main\\resources\\rules.txt";
    public static final String GET_WINNER_MESSAGE = "Would you like to find the winners against the dealer? e.g. y or n";
    public static final String ANOTHER_CARD = " would you like another card? e.g y or n";
    public static final String YES = "yes";
    public static final String Y = "y";
    public static final String AN_ERROR_OCCURRED = "An error occurred. The file could not be found...";
    public static final String HOW_MANY_PLAYERS = "How many players are there? e.g. 4";

    public static final String DEALERS_HAND_IS_BIGGER_THAN_21 = "Dealers hand is bigger than 21";
    public static final int TWENTY_ONE = 21;
    public static final String PLEASE_ENTER_YOUR_NAME = " please enter your name?";
    public static final String PLAYER = "Player ";
    public static final String SHORTER_THAN_3_CHARACTERS_PLEASE_TRY_AGAIN = "Oh no your name is shorter than 3 characters please try again.";

    public static final String NEW_LINE = "\n";
    public static final String PLEASE_ENTER_A_VALID_NUMBER = "Please enter a valid number";
    public static final String LINE_BREAK = "-----------------------------------------------------------------------";
    public static final String BLANK_STRING = "";
    public static final String SORRY_YOUR_CARDS_VALUE_IS_NOW = "Sorry your cards value is now ";
    public static final String NEXT_GAME = " you are out and can join the next game.";

    public static void main(String[] args) {
        printOutRulesFromFile();
        BlackJack21 blackJack21 = new BlackJack21();
        List<Player> players = getPlayers(getNumberOfPlayers());
        blackJack21.play(new Dealer(), players);
    }

    private static void printOutRulesFromFile() {
        try {
            File myObj = new File(PATH_TO_RULES_FILE);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(AN_ERROR_OCCURRED);
            e.printStackTrace();
        }
    }

    public static List<Player> getPlayers(int numberOfPlayers) {
        List<Player> players = new ArrayList<>();
        for (int j = 0; j < numberOfPlayers; j++) {
            System.out.println(PLAYER + (j + 1) + PLEASE_ENTER_YOUR_NAME);
            String playerName = getPlayersName();
            players.add(Player.builder()
                    .name(playerName)
                    .build());
        }
        return players;
    }

    public static String getPlayersName() {
        Scanner sc = new Scanner(System.in);
        String playerName = sc.next();
        if (BLANK_STRING.equals(playerName) || playerName.length() < 3) {
            System.out.println(SHORTER_THAN_3_CHARACTERS_PLEASE_TRY_AGAIN);
            playerName = sc.next();
        }
        return playerName;
    }

    public static int getNumberOfPlayers() {
        Scanner numberOfPlayersInput = new Scanner(System.in);
        boolean invalid = true;
        int numberOfPlayers = 0;
        do {
            try {
                System.out.println(HOW_MANY_PLAYERS);
                numberOfPlayers = numberOfPlayersInput.nextInt();
                if (25 < numberOfPlayers) {
                    numberOfPlayers = 0;
                    System.out.println(TOO_MANY_PLAYERS);
                } else {
                    invalid = false;
                }
                if (invalid) {
                    System.out.println(PLEASE_ENTER_A_VALID_NUMBER);
                }
            } catch (Exception e) {
                numberOfPlayersInput.next();
            }
        } while (invalid);
        return numberOfPlayers;
    }

    private void findWinnersAgainstTheDealer(Dealer dealer, List<Player> players) {
        if (dealer.getCardValue() > TWENTY_ONE) {
            System.out.println(DEALERS_HAND_IS_BIGGER_THAN_21);
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println(NEW_LINE);
            System.out.println(LINE_BREAK);
            Player player = players.get(i);
            player.printCards();
            dealer.getWinner(player);
        }
    }

    public void play(Dealer dealer, List<Player> players) {
        dealer.handOutCards(players);
        dealer.printTopCard();

        boolean keepPlaying = true;
        int currentPlayerIndex = 0;
        while (keepPlaying) {
            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.printCards();
            System.out.println(currentPlayer.getName() + ANOTHER_CARD);
            if (doesThePlayerWantAnotherCard()) {
                dealer.handOutCard(currentPlayer);
                Integer calculateCardsValue = dealer.calculateCardsValue(currentPlayer.getCards());
                if (TWENTY_ONE < calculateCardsValue) {
                    players.remove(currentPlayer);
                    System.out.println(SORRY_YOUR_CARDS_VALUE_IS_NOW + calculateCardsValue + NEXT_GAME);
                } else if (5 <= currentPlayer.getCards().size()) {
                    dealer.getWinner(currentPlayer);
                    players.remove(currentPlayer);
                }
                System.out.println(NEW_LINE);
                currentPlayer.printCards();
            }

            if (players.size() == 0) {
                keepPlaying = false;
            } else {
                currentPlayerIndex++;

                if (players.size() == currentPlayerIndex) {
                    System.out.println(GET_WINNER_MESSAGE);
                    Scanner sc = new Scanner(System.in);
                    String endGame = sc.next();
                    if (Y.equalsIgnoreCase(endGame) || YES.equalsIgnoreCase(endGame)) {
                        keepPlaying = false;
                        dealer.printCards();
                        findWinnersAgainstTheDealer(dealer, players);
                    }
                    currentPlayerIndex = 0;
                }
            }
        }
        System.exit(1);
    }

    private boolean doesThePlayerWantAnotherCard() {
        Scanner sc = new Scanner(System.in);
        String anotherCard = sc.next();
        if (Y.equalsIgnoreCase(anotherCard) || YES.equalsIgnoreCase(anotherCard)) {
            return true;
        }
        return false;
    }

}
