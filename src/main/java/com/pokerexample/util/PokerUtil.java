package com.pokerexample.util;

import com.pokerexample.poker.entity.Card;
import com.pokerexample.poker.entity.CardSuit;
import com.pokerexample.poker.entity.CardValue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerUtil {
    private PokerUtil() {
    }

    public static void removePair(List<Card> cards) {
        removeSameCardValuesByQuantity(cards, 2);
    }

    public static void removeSameCardValuesByQuantity(List<Card> cards, int quantity) {
        CardValue pairValue = getSameCardValues(cards, quantity);
        cards.removeIf(card -> card.getValue().equals(pairValue));
    }

    public static boolean hasSameCardValues(List<Card> cards, int quantity) {
        Map<CardValue, Long> cardValueCounts = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        return cardValueCounts.containsValue((long) quantity);
    }

    public static long amountOfCardValues(List<Card> cards, CardValue value) {
        return cards.stream()
                .filter(card -> card.getValue() == value)
                .count();
    }

    public static long amountOfCardSuits(List<Card> cards, CardSuit suit) {
        return cards.stream()
                .filter(card -> card.getSuit() == suit)
                .count();
    }

    public static boolean hasValues(List<Card> cards, List<CardValue> valuesToCheck) {
        List<CardValue> handValues = cards.stream()
                .map(Card::getValue)
                .collect(Collectors.toList());
        return new HashSet<>(handValues).containsAll(valuesToCheck);
    }

    public static CardValue getSameCardValues(List<Card> cards, int quantity) {
        for (CardValue cardValue : CardValue.values()) {
            if (amountOfCardValues(cards, cardValue) == quantity) return cardValue;
        }
        throw new IllegalArgumentException("No cards found");
    }
}
