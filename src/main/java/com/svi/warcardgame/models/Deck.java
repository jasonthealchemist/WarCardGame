package com.svi.warcardgame.models;

import com.svi.warcardgame.enums.Ranks;
import com.svi.warcardgame.enums.Suits;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * The Deck class is responsible for loading and validating the deck from file, shuffling, input shuffling times and roundrobin distribution to the players.
 */

public class Deck {
    private final List<Card> cards;

    public static final int DECK_SIZE =
            Ranks.values().length * Suits.values().length;

    // Gets the length of the cards_per_row to display in rows
    public static final int CARDS_PER_ROW = Ranks.values().length;

    public static final int MIN_SHUFFLES = 1;
    public static final int MAX_SHUFFLES = 100;

    public boolean loadFromFile(String fileName) {
        List<Card> loadedCards = new ArrayList<>();
        Set<String> uniqueCards = new HashSet<>();

        /*
        * Read the file using BufferedReader to read the file deck line by line,
        * */
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Deck.class.getResourceAsStream("/input.txt"))
        ))) {

            String line;


            // Validation check if the file deck is valid or invalid.
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer =
                        new StringTokenizer(line, " \t,");

                // check if another card exists and retrives the next card
                while (tokenizer.hasMoreTokens()) {
                    String cardCode = tokenizer.nextToken();
                    String[] parts = cardCode.split("-", -1);

                    if (parts.length != 2) {
                        System.out.println(
                                "Invalid card format: " + cardCode);
                        return false;
                    }

                    Suits suit;
                    Ranks rank;

                    try{
                        suit = Suits.fromSymbol(parts[0]);
                        rank = Ranks.fromSymbol(parts[1]);

                    }catch (IllegalArgumentException e) {
                        System.out.println("Error: Invalid card: " + cardCode);
                        return false;
                    }

                    if (!uniqueCards.add(cardCode)) {
                        System.out.println(
                                "Error: Duplicate card: " + cardCode);
                        return false;
                    }

                    loadedCards.add(new Card(rank, suit));
                }
            }

            if (loadedCards.size() != DECK_SIZE) {
                System.out.println(
                        "Error: The deck must contain exactly 52 cards.");
                return false;
            }


            cards.clear();
            cards.addAll(loadedCards);

            return true;

        } catch (IOException e) {
            System.out.println("Error: Unable to read file: " + fileName);
            System.out.println("Details: " + e.getMessage());
            return false;
        }
    }

    // create new card ArrayList
    public Deck() {
        cards = new ArrayList<>();
    }


    // Perfect shuffle, cuts the deck in half and distribute the card in roundrobin
    public void shuffle() {
        int middle = cards.size() / 2;

        List<Card> firstHalf = new ArrayList<>(
                cards.subList(0, middle)
        );

        List<Card> secondHalf = new ArrayList<>(
                cards.subList(middle, cards.size())
        );

        List<Card> shuffledCards = new ArrayList<>();

        for (int i = 0; i < middle; i++) {
            shuffledCards.add(firstHalf.get(i));
            shuffledCards.add(secondHalf.get(i));
        }

        cards.clear();
        cards.addAll(shuffledCards);
    }

    // Handles the shufflingTimes
    public void shuffleMultipleTimes(int shuffleCount) {

        if (shuffleCount < MIN_SHUFFLES || shuffleCount > MAX_SHUFFLES) {
            System.out.println("Error: Shuffle count must be between " + MIN_SHUFFLES + " and " +  MAX_SHUFFLES + ".");
            return;
        }
        for (int count = 0; count < shuffleCount; count++) {
            shuffle();
        }
    }


    public void distributeCards(List<Player> players) {
        if (players.isEmpty()) {
            System.out.println("There are no players to receive cards");
            return;
        }
        int playerIndex = 0;

        for (Card card : cards) {
            players.get(playerIndex).addToBottom(card);

            playerIndex++;

            if (playerIndex == players.size()) {
                playerIndex = 0;
            }
        }
        cards.clear();
    }

    public void displayDeck(String title) {
        System.out.println("\n" + title);

        int cardCount = 0;

        for (Card card : cards) {
            System.out.print(card + " ");
            cardCount++;

            if (cardCount % CARDS_PER_ROW == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
}

