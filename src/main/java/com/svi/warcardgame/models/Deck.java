package com.svi.warcardgame.models;

import com.svi.warcardgame.enums.Ranks;
import com.svi.warcardgame.enums.Suits;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Deck {
    private final List<Card> cards;

    public boolean loadFromFile(String fileName) {
        List<Card> loadedCards = new ArrayList<>();
        Set<String> uniqueCards = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader("WarCardGame/src/main/resources/" + fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer =
                        new StringTokenizer(line, " \t,");

                while (tokenizer.hasMoreTokens()) {
                    String cardCode = tokenizer.nextToken();
                    String[] parts = cardCode.split("-", -1);

                    if (parts.length != 2) {
                        System.out.println(
                                "Invalid card format: " + cardCode);
                        return false;
                    }

                    Suits suit = Suits.fromSymbol(parts[0]);
                    Ranks rank = Ranks.fromSymbol(parts[1]);

                    if (suit == null || rank == null) {
                        System.out.println(
                                "Error: Invalid card: " + cardCode);
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

            if (loadedCards.size() != 52) {
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

    public Deck(){
        cards = new ArrayList<>();
    }
    public void addCard(Card card){
        if (card != null){
            cards.add(card);
        }
    }
    public int getCardsCount(){
        return cards.size();
    }

    public List<Card> getCards(){
        return new  ArrayList<>(cards);
    }

    public void shuffle(){
        int middle = cards.size() / 2;

        List<Card> firstHalf = new ArrayList<>(
                cards.subList(0, middle)
        );

        List<Card> secondHalf = new ArrayList<>(
                cards.subList(middle, cards.size())
        );

        List<Card> shuffledCards = new ArrayList<>();

        for (int index = 0; index < middle; index++){
            shuffledCards.add(firstHalf.get(index));
            shuffledCards.add(secondHalf.get(index));
        }

        cards.clear();
        cards.addAll(shuffledCards);
    }

    public void shuffleMultipleTimes(int shuffleCount){
        if  (shuffleCount < 0){
            System.out.println("Shuffle count cannot be negative.");
            return;
        }

        for (int count = 0; count < shuffleCount; count++){
                shuffle();
        }
    }

    public void distributeCards(List<Player> players){
        if(players.isEmpty()){
            System.out.println("There are no players to receive cards");
            return;
        }
        int playerIndex = 0;

        for(Card card : cards) {
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

            if (cardCount % 13 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
}

