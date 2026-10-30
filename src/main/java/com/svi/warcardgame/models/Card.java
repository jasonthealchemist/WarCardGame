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

        int rankComparison = Integer.compare(
                a.getRank().getValue(),
                b.getRank().getValue());


        if (rankComparison != 0){
            return rankComparison;
        }

        return Integer.compare(
                a.getSuit().getValue(),
                b.getSuit().getValue()
        );
    }

    @Override
    public String toString() {
        return  rank.getSymbol() + "-" + suit.getSymbol();
    }

    public String toDisplayString(){
        return rank.getSymbol() + "-" + suit.getDisplaySymbol();
    }
}
