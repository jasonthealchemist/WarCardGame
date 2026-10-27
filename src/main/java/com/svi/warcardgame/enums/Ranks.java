package com.svi.warcardgame.enums;

public enum Ranks {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String symbol;
    private final int value;

    Ranks(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {return symbol;}
    public int getValue() {return this.value;}

    public static Ranks fromSymbol(String text) {
        for (Ranks rank : values()){
            if(rank.symbol.equals(text)) {
                return rank;
            }
        }
        return null;
    }
}
