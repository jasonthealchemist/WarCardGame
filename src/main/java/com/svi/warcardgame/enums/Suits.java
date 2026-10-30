package com.svi.warcardgame.enums;

public enum Suits {
    DIAMOND('D', 4),
    HEART('H', 3),
    SPADE('S', 2),
    CLUBS('C', 1);

    private final char symbol;
    private final int value;


    Suits(char symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public char getSymbol() {return symbol;}
    public int getValue() {return value;}


    public static Suits fromSymbol(String text) {
        if(text == null){
            throw new IllegalArgumentException("Suit symbol cannot be null");
        }
        for (Suits suit : values()) {
            if (suit.symbol == text.charAt(0)){
                return suit;

            }
        }
        throw new IllegalArgumentException("Invalid suit symbol: " + text);
    }

    public String getDisplaySymbol(){
        switch (this) {
            case DIAMOND:
                return "♦";
            case HEART:
                return "♥";
            case SPADE:
                return "♠";
            case CLUBS:
                return "♣";
            default:
                return "";
        }

    }
}

