package com.svi.warcardgame.enums;

public enum Suits {
    CLUBS('C', 1),
    SPADE('S', 2),
    HEART('H', 3),
    DIAMOND('D', 4);

    private final char symbol;
    private final int value;


    Suits(char symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public char getSymbol() {return symbol;}
    public int getValue() {return value;}


    public static Suits fromSymbol(String text) {
        if (text == null || text.length() != 1) {
            return null;
        }
        for (Suits suit : values()) {
            if (suit.symbol == text.charAt(0)){
                return suit;

            }
        }
        return null;
    }
}
