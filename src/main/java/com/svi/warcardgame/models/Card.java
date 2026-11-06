package com.svi.warcardgame.models;

import com.svi.warcardgame.enums.Ranks;
import com.svi.warcardgame.enums.Suits;


/*
* Handles the comparison method and display of the cards.
*
* */
public class Card{
    private final Ranks rank;
    private final Suits suit;
    private static final String SYMBOL_SEPARATOR = "-";
    private static final String DISPLAY_SEPARATOR = " ";


    public Card(Ranks rank, Suits suit) {
        this.rank = rank;
        this.suit = suit;
    }


    public Ranks getRank() {return rank;}
    public Suits getSuit() {return suit;}

    public static int compareCard(Card a, Card b){

        int rankComparison = Integer.compare(a.getRank().getValue(),
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
        return suit.getDisplaySymbol() + suit.getSymbol() + SYMBOL_SEPARATOR + rank.getSymbol()
                + DISPLAY_SEPARATOR;
    }

    public String toDisplayString(){
        return suit.getDisplaySymbol() + suit.getSymbol() + "-" + rank.getSymbol();
    }
}
