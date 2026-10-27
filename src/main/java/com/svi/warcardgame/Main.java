package com.svi.warcardgame;

import com.svi.warcardgame.enums.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(Suits.fromSymbol("D"));              // DIAMOND
        System.out.println(Ranks.fromSymbol("10").getValue());  // 10
        System.out.println(Ranks.fromSymbol("X"));
    }
}
