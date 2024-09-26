package com.pokerexample.poker.entity;

import java.util.HashMap;
import java.util.Map;

public enum CardValue {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    private static final Map<Character, CardValue> VALUE_MAP = new HashMap<>();
    private final char symbol;

    static {
        for (CardValue cardValue : values()) {
            VALUE_MAP.put(cardValue.symbol, cardValue);
        }
    }

    CardValue(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static CardValue fromChar(char symbol) {
        CardValue cardValue = VALUE_MAP.get(symbol);
        if (cardValue == null) {
            throw new IllegalArgumentException("Invalid card value: " + symbol);
        }
        return cardValue;
    }

    @Override
    public String toString() {
        return "CardValue{" +
                "symbol=" + symbol +
                '}';
    }
}
