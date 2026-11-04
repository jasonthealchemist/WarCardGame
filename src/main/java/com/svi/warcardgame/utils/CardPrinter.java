package com.svi.warcardgame.utils;

import com.svi.warcardgame.enums.Ranks;
import com.svi.warcardgame.models.Card;

public final class CardPrinter {
    // Gets the length of the cards_per_row to display in rows
    public static final int CARDS_PER_ROW = Ranks.values().length;


    public static void printCards(Iterable<Card> cards) {
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
