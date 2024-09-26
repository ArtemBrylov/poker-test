package com.pokerexample.poker.entity;

import java.util.HashMap;
import java.util.Map;

public enum CardSuit {
    SPADES('S'),
    HEARTS('H'),
    DIAMONDS('D'),
    CLUBS('C');

    private static final Map<Character, CardSuit> VALUE_MAP = new HashMap<>();
    private final char symbol;

    static {
        for (CardSuit cardSuit : values()) {
            VALUE_MAP.put(cardSuit.symbol, cardSuit);
        }
    }

    CardSuit(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static CardSuit fromChar(char suit) {
        CardSuit cardSuit = VALUE_MAP.get(suit);
        if (cardSuit == null) {
            throw new IllegalArgumentException("Invalid card suit: " + suit);
        }
        return cardSuit;
    }

    @Override
    public String toString() {
        return "CardSuit{" +
                "symbol=" + symbol +
                '}';
    }
}
