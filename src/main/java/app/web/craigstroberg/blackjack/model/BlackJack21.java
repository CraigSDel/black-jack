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
    public static final String PLAYER_WON_MESSAGE = " you won against the dealer! Winner Winner Chicken Dinner :) ";
    public static final String IT_IS_A_DRAW = "It is a draw!";
    public static final String DEALERS_HAND_IS_BIGGER_THAN_21 = "Dealers hand is bigger than 21";
    public static final int TWENTY_ONE = 21;
    public static final String VALUES_ARE_BIGGER_THAN_21 = "Oh no your cards values are bigger than 21";
    public static final String DEALER_WINS_THIS_ROUND = "Dealer wins this round!";
    public static final String PLEASE_ENTER_YOUR_NAME = " please enter your name?";
    public static final String PLAYER = "Player ";
    public static final String SHORTER_THAN_3_CHARACTERS_PLEASE_TRY_AGAIN = "Oh no your name is shorter than 3 characters please try again.";
    public static final String DEALERS_HAND_EQUALS = "Dealers hand equals ";
    public static final String PLAYERS_HAND_EQUALS = "Players hand equals ";
    public static final String NEW_LINE = "\n";
    public static final String PLEASE_ENTER_A_VALID_NUMBER = "Please enter a valid number";

    public static void main(String[] args) {
        printOutRulesFromFile();
        Scanner sc = new Scanner(System.in);
        int numberOfPlayers = getNumberOfPlayers(sc);

        Dealer dealer = new Dealer();
        List<Player> players = new ArrayList<>();

        for (int j = 0; j < numberOfPlayers; j++) {
            System.out.println(PLAYER + (j + 1) + PLEASE_ENTER_YOUR_NAME);
            String playerName = sc.next();
            if ("".equals(playerName) || playerName.length() < 3) {
                System.out.println(SHORTER_THAN_3_CHARACTERS_PLEASE_TRY_AGAIN);
                playerName = sc.next();
            }
            players.add(Player.builder()
                    .name(playerName)
                    .build());
        }

        dealer.handOutCards(players);

        boolean keepPlaying = true;
        int currentPlayerIndex = 0;
        while (keepPlaying) {
            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.printCards();
            System.out.println(currentPlayer.getName() + ANOTHER_CARD);
            String anotherCard = sc.next();
            if (Y.equalsIgnoreCase(anotherCard) || YES.equalsIgnoreCase(anotherCard)) {
                dealer.handOutCard(currentPlayer);
                System.out.println(NEW_LINE);
                currentPlayer.printCards();
            }
            currentPlayerIndex++;

            if (numberOfPlayers == currentPlayerIndex) {
                System.out.println(GET_WINNER_MESSAGE);
                String endGame = sc.next();
                if (Y.equalsIgnoreCase(endGame) || YES.equalsIgnoreCase(endGame)) {
                    keepPlaying = false;
                    dealer.printCards();
                    findWinnersAgainstTheDealer(numberOfPlayers, dealer, players);
                }
                currentPlayerIndex = 0;
            }
        }
    }

    private static int getNumberOfPlayers(Scanner sc) {
        boolean invalid = true;
        int numberOfPlayers = 0;
        do {
            try {
                System.out.println(HOW_MANY_PLAYERS);
                numberOfPlayers = sc.nextInt();
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
                sc.next();
            }
        } while (invalid);
        return numberOfPlayers;
    }

    private static void findWinnersAgainstTheDealer(int numberOfPlayers, Dealer dealer, List<Player> players) {
        Integer dealerCardValue = dealer.getCardValue();
        if (dealerCardValue > TWENTY_ONE) {
            System.out.println(DEALERS_HAND_IS_BIGGER_THAN_21);
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = players.get(i);
            player.printCards();
            Integer playerCardValue = dealer.calculateCardsValue(player.getCards());
            System.out.println(NEW_LINE + DEALERS_HAND_EQUALS + dealerCardValue
                    + NEW_LINE + PLAYERS_HAND_EQUALS + playerCardValue);
            if (playerCardValue > TWENTY_ONE) {
                System.out.println(NEW_LINE + VALUES_ARE_BIGGER_THAN_21);
            } else if (dealerCardValue < playerCardValue) {
                System.out.println(player.getName() + PLAYER_WON_MESSAGE);
            } else if (dealerCardValue > playerCardValue) {
                System.out.println(NEW_LINE + DEALER_WINS_THIS_ROUND);
            } else if (dealerCardValue == playerCardValue) {
                System.out.println(NEW_LINE + IT_IS_A_DRAW);
            }
        }
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
}
