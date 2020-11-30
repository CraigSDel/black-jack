package app.web.craigstroberg.blackjack.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack21 {

    public static final String TOO_MANY_PLAYERS = "There are too many players as each player will not be able to get two cards each";
    public static final String PATH_TO_RULES_FILE = "src\\main\\resources\\rules.txt";

    public static void main(String[] args) {
        printOutRulesFromFile();
        System.out.println("How many players are there? e.g. 4");
        Scanner sc = new Scanner(System.in);
        int numberOfPlayers = sc.nextInt();
        Dealer dealer = new Dealer();
        List<Player> players = new ArrayList<>();

        for (int j = 0; j < numberOfPlayers; j++) {
            System.out.println("Player " + (j + 1) + " please enter your name?");
            players.add(Player.builder()
                    .name(sc.next())
                    .build());
        }

        dealer.handOutCards(players);

        boolean keepPlaying = true;
        int currentPlayer = 0;
        while (keepPlaying) {
            if (numberOfPlayers == currentPlayer) {
                currentPlayer = 0;
            }
            players.get(currentPlayer).printCards();
            System.out.println(players.get(currentPlayer).getName() + " would you like another card? e.g y or n");
            String anotherCard = sc.nextLine();
            switch (anotherCard){
                case "y": dealer.handOutCard(players.get(currentPlayer));
            }
            currentPlayer++;
        }
        System.out.println(dealer.toString());
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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
