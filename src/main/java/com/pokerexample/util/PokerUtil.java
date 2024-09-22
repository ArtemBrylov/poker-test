package com.pokerexample.util;

import com.pokerexample.poker.CardValue;
import com.pokerexample.poker.PokerHand;

import java.util.List;
import java.util.stream.Collectors;

import static com.pokerexample.poker.HandAnalyser.getSameCardValues;

public class PokerUtil {
    private PokerUtil() {
    }

    public static PokerHand removePair(PokerHand hand) {
        return removeSameCardValuesByQuantity(hand, 2);
    }

    public static PokerHand removeSameCardValuesByQuantity(PokerHand hand, int quantity) {
        Character pairChar = getSameCardValues(hand, quantity);
        String cardsWithoutOnePair = removeCardByValue(hand.getCards().split(" "), pairChar);
        return new PokerHand(cardsWithoutOnePair);
    }

    public static String removeCardByValue(String[] cards, char cardValue) {
        StringBuilder result = new StringBuilder();

        for (String card : cards) {
            if (card.charAt(0) != cardValue) {
                result.append(card).append(" ");
            }
        }

        return result.toString().trim();
    }

    public static String removeOneCardByValue(String[] cards, char cardValue) {
        StringBuilder result = new StringBuilder();
        int deletedCardCount = 0;

        for (String card : cards) {
            if (card.charAt(0) == cardValue && deletedCardCount == 0) {
                deletedCardCount++;
                continue;
            }
            result.append(card).append(" ");
        }

        return result.toString().trim();
    }

    public static boolean hasSameCardValues(PokerHand hand, int quantity) {
        for (Character ch : CardValue.getValues()) {
            if (amountOfChars(hand, ch) == quantity) return true;
        }
        return false;
    }

    public static long amountOfChars(PokerHand hand, char ch) {
        return hand.getCards().chars()
                .filter(c -> c == ch)
                .count();
    }

    public static List<Character> filteredChars(PokerHand hand, char ch) {
        return hand.getCards().chars()
                .filter(c -> c == ch)
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }

    public static boolean hasFiveValues(String cards,
                                         String firstValue,
                                         String secondValue,
                                         String thirdValue,
                                         String forthValue,
                                         String fifthValue) {
        return cards.contains(firstValue)
                && cards.contains(secondValue)
                && cards.contains(thirdValue)
                && cards.contains(forthValue)
                && cards.contains(fifthValue);
    }
}
