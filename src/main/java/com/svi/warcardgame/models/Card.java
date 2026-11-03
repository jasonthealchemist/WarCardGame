package com.svi.warcardgame.models;

import com.svi.warcardgame.enums.Ranks;
import com.svi.warcardgame.enums.Suits;


/*
* Handles the comparison method and display of the cards.
*
* Uses interface and polymorphism Comparable to provide compareTo inside the Card class not in the game loop.
* */
public class Card  implements Comparable<Card>{
    private final Ranks rank;
    private final Suits suit;
    private static final String SYMBOL_SEPARATOR = "-";
    private static final String DISPLAY_SEPARATOR = " ";


    public Card(Ranks rank, Suits suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card other){

        int rankComparison = Integer.compare(rank.getValue(),
                other.rank.getValue());


        if (rankComparison != 0){
            return rankComparison;
        }

        return Integer.compare(
                suit.getValue(),
                suit.getValue()
        );
    }

    @Override
    public String toString() {
        return suit.getDisplaySymbol() + suit.getSymbol() + SYMBOL_SEPARATOR + rank.getSymbol()
                + DISPLAY_SEPARATOR;
    }

    public String toDisplayString(){
        return suit.getDisplaySymbol() + "-" + rank.getSymbol();
    }
}
