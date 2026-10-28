package com.svi.warcardgame.enums;

public enum Ranks {
    ACE("A", 14),
    KING("K", 13),
    QUEEN("Q", 12),
    JACK("J", 11),
    TEN("10", 10),
    NINE("9", 9),
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2);

    private final String symbol;
    private final int value;

    Ranks(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {return symbol;}
    public int getValue() {return this.value;}

    public static Ranks fromSymbol(String text) {
        if (text == null){
            throw new IllegalArgumentException(
                    "Rank symbol cannot be null"
            );
        }
        for (Ranks rank : values()){
            if(rank.symbol.equalsIgnoreCase(text.trim())) {
                return rank;
            }
        }
        throw new IllegalArgumentException(
                "Invalid rank symbol: " + text
        );
    }
}
