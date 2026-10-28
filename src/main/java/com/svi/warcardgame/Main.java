package com.svi.warcardgame;

import com.svi.warcardgame.game.GameUtils;
import com.svi.warcardgame.models.Deck;
import com.svi.warcardgame.models.Player;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();

        if(deck.loadFromFile("input.txt")){
            deck.displayDeck("Original Deck");
        }else {
            System.out.println("Failed to load Deck");
        }
    }
}
