package com.svi.warcardgame.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;


public class Player {
    private final int playerNo;
    private final Deque<Card> cards;

    public Player(int playerNo) {
        this.playerNo = playerNo;
        this.cards = new ArrayDeque<>();
    }

    public int getPlayerNo() {
        return playerNo;
    }

    /*public int getCardCount(){
        return cards.size();
    } */

    public boolean hasCards(){
        return !cards.isEmpty();
    }

    public boolean hasAllCards(int totalCards){
        return cards.size() == totalCards;
    }

    public void addToBottom(Card card){
        if (card != null){
            cards.addLast(card);
        }
    }

    public Card playTopCard(){
        if(!cards.isEmpty()){
            return cards.removeFirst();
        }
        return null;
    }

    public void collectCards(List<Card> playedCards, Card winningCard){
        addToBottom(winningCard);

        for(Card card : playedCards){
            if (card != winningCard){
                addToBottom(card);
            }
        }
    }

    public void displayDeck() {
        int cardNumber = 1;

        for (Card card : cards) {
            System.out.print(card + " ");

            if (cardNumber % 13 == 0) {
                System.out.println();
            }

            cardNumber++;
        }

        System.out.println();
    }
}
