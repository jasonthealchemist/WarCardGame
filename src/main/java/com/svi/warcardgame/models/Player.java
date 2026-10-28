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

    public int getCardCount(){
        return cards.size();
    }

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

}
