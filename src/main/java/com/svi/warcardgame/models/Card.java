package com.svi.warcardgame.models;

import com.svi.warcardgame.enums.Ranks;
import com.svi.warcardgame.enums.Suits;

public class Card {
    private final Ranks rank;
    private final Suits suit;


    public Card(Ranks rank, Suits suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Ranks getRank() {return rank;}
    public Suits getSuit() {return suit;}

    public static int compareCard(Card a, Card b){

        if (a.getRank().getValue() > b.getRank().getValue()){
            return 1;
        }
        else if (a.getRank().getValue() < b.getRank().getValue()){
            return -1;
        }

        if (a.getSuit().getValue() > b.getSuit().getValue()){
            return 1;
        }
        else if(a.getSuit().getValue() < b.getSuit().getValue()){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return suit.getSymbol() + "-"  + rank.getSymbol();
    }
}
