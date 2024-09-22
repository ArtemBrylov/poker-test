package com.pokerexample.poker;

import java.util.List;

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

    private final int value;

    CardValue(int value) {
        this.value = value;
    }

    public static List<Character> getValues() {
        return List.of('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A');
    }

    public static CardValue fromChar(char value) {
        switch (value) {
            case '2': return TWO;
            case '3': return THREE;
            case '4': return FOUR;
            case '5': return FIVE;
            case '6': return SIX;
            case '7': return SEVEN;
            case '8': return EIGHT;
            case '9': return NINE;
            case 'T': return TEN;
            case 'J': return JACK;
            case 'Q': return QUEEN;
            case 'K': return KING;
            case 'A': return ACE;
            default: throw new IllegalArgumentException("Invalid card value: " + value);
        }

    }
}
