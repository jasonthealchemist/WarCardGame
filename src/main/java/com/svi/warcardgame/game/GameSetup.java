package com.svi.warcardgame.game;

import com.svi.warcardgame.models.Deck;
import com.svi.warcardgame.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSetup {
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 8;
    private static final int TOTAL_CARDS = 52;

    //colors
    private static final String RESET = "\u001B[0m";
    private static final String GOLD = "\u001B[33m";

    private final Scanner scanner;


    public GameSetup(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean pressEnterToPlay(){
        while(true){
            System.out.print("Press Enter to Start | Q to Quit: ");
            String input = scanner.nextLine().trim();

            if(input.isEmpty()){
                System.out.println("Game Starting....");
                return true;
            }

            if(input.equalsIgnoreCase("Q") ||  input.equalsIgnoreCase("QUIT")){
                if(confirmQuit()){
                    System.out.println("Exiting War Card Game.....");
                    return false;
                }
            }else {
                System.out.println("Invalid, Please press Enter");
            }
        }
    }

    public boolean confirmQuit(){
        while(true){
            System.out.print("Are you sure you want to quit? (Y/N): ");
            String answer = scanner.nextLine().trim();


            if(answer.equalsIgnoreCase("Y") ||  answer.equalsIgnoreCase("YES")){
                return true;
            }

            if(answer.equalsIgnoreCase("N") ||  answer.equalsIgnoreCase("NO")){
                return false;
            }

            System.out.println("Invalid, Please enter Y or N");

        }
    }


    public void start(){

        if (!pressEnterToPlay()){
            return;
        }

        while(true){
            boolean gameStarted = startNewGame();

            if(!gameStarted){
                return;
            }

            if(!askToPlayAgain()){
                System.out.println("Thank you for playing!");
                return;
            }


        }
    }

    private boolean startNewGame(){
        Deck deck = new Deck();

        System.out.println("Loading deck from file...");

        if (!deck.loadFromFile("input.txt")){
            System.out.println("Check the file and try again");
            return false;
        }

        System.out.println("Deck loaded successfully!");
        System.out.println(" ");

        int numberOfPlayers = getNumberOfPlayers();
        int shuffleDeck = getshuffleDeck();

        deck.displayDeck("Original Deck");

        deck.shuffleMultipleTimes(shuffleDeck);

        deck.displayDeck("Deck after shuffling");

        List<Player> players = createPlayers(numberOfPlayers);

        deck.distributeCards(players);

        WarCardGame game = new WarCardGame();

        Player winner = game.playGame(players, TOTAL_CARDS);

        if(winner != null){
            System.out.println(GOLD + "★ Game Winner: Player " + winner.getPlayerNo() + " ★"+ RESET);
            System.out.println(" ");
        }
        return true;
    }

    private int getNumberOfPlayers(){
        while(true){
            System.out.print("Enter Number of Players: ");
            String input = scanner.nextLine().trim();

            try{
                int num_of_players = Integer.parseInt(input);

                if(num_of_players >= MIN_PLAYERS && num_of_players <= MAX_PLAYERS){
                    return num_of_players;
                }
                System.out.println(
                        "Number of players must be between 2 and 8 to start playing."
                );
            }catch (NumberFormatException e){
                System.out.println(
                        "Please enter a whole number"
                );
            }
        }
    }

    private List<Player> createPlayers(int numberOfPlayers){
        List<Player> players = new ArrayList<>();

        for(int playerCount = 1; playerCount <= numberOfPlayers; playerCount++){
            players.add(new Player(playerCount));
        }
        return players;
    }

    private int getshuffleDeck(){
        while(true){
            System.out.print("Enter number of shuffles: ");
            String input = scanner.nextLine().trim();

            try{
                int getshuffleDeck = Integer.parseInt(input);

                if(getshuffleDeck >= 0){
                    return getshuffleDeck;
                }
                System.out.println(
                        "Shuffle Count cannot be negative."
                );
            }catch (NumberFormatException e){
                System.out.println(
                        "Please enter a whole number"
                );
            }
        }
    }

    private boolean askToPlayAgain(){
        while(true){
            System.out.print("Would you like to play again? (Y/N): ");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("YES")){
                return true;
            }
            if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("NO")){
                return false;
            }
            System.out.println(
                    "Error: Please enter Y/YES or N/NO."
            );
        }
    }
}
