package com.pokerexample.poker;

import java.util.Arrays;
import java.util.Comparator;

import static com.pokerexample.util.PokerUtil.*;

public class HandAnalyser {
    private HandAnalyser() {
    }

    public static boolean hasRoyalFlush(PokerHand hand) {
        return hasSameSuit(hand) && isHighestStraight(hand);
    }

    public static boolean hasStraightFlush(PokerHand hand) {
        return hasSameSuit(hand) && hasStraight(hand);
    }

    public static boolean hasFourOfAKind(PokerHand hand) {
        return hasSameCardValues(hand, 4);
    }

    public static boolean hasFullHouse(PokerHand hand) {
        return hasThreeOfAKind(hand) && hasPair(hand);
    }

    public static boolean hasFlush(PokerHand hand) {
        return hasSameSuit(hand);
    }

    public static boolean hasStraight(PokerHand hand) {
        return hasFiveValues(hand.getCards(), "2", "3", "4", "5", "6")
                || hasFiveValues(hand.getCards(), "3", "4", "5", "6", "7")
                || hasFiveValues(hand.getCards(), "4", "5", "6", "7", "8")
                || hasFiveValues(hand.getCards(), "5", "6", "7", "8", "9")
                || hasFiveValues(hand.getCards(), "6", "7", "8", "9", "T")
                || hasFiveValues(hand.getCards(), "7", "8", "9", "T", "J")
                || hasFiveValues(hand.getCards(), "8", "9", "T", "J", "Q")
                || hasFiveValues(hand.getCards(), "9", "T", "J", "Q", "K")
                || hasFiveValues(hand.getCards(), "T", "J", "Q", "K", "A")
                || hasFiveValues(hand.getCards(), "A", "2", "3", "4", "5");
    }

    public static boolean hasThreeOfAKind(PokerHand hand) {
        return hasSameCardValues(hand, 3);
    }

    public static boolean hasTwoPairs(PokerHand hand) {
        if (hasPair(hand)) {
            String cards = hand.getCards();
            Character pairChar = getSameCardValues(hand, 2);

            String cardsWithoutOnePair = cards.replace(pairChar, ' ');
            return hasPair(new PokerHand(cardsWithoutOnePair));
        }
        return false;
    }

    public static boolean hasPair(PokerHand hand) {
        return hasSameCardValues(hand, 2);
    }

    public static String getHighCard(PokerHand hand) {
        return getHighCard(hand.getCards());
    }

    public static String getHighCard(String hand) {
        String[] cards = hand.split(" ");

        return Arrays.stream(cards)
                .max(Comparator.comparing(card -> CardValue.fromChar(card.charAt(0)).ordinal()))
                .orElseThrow(() -> new IllegalArgumentException("No cards provided"));
    }

    public static Character getSameCardValues(PokerHand hand, int quantity) {
        for (Character ch : CardValue.getValues()) {
            if (amountOfChars(hand, ch) == quantity) return ch;
        }
        return '0';
    }

    private static boolean isHighestStraight(PokerHand hand) {
        return hasFiveValues(hand.getCards(), "T", "J", "Q", "K", "A");
    }

    private static boolean hasSameSuit(PokerHand hand) {
        return amountOfChars(hand, 'S') == 5
                || amountOfChars(hand, 'H') == 5
                || amountOfChars(hand, 'D') == 5
                || amountOfChars(hand, 'C') == 5;
    }
}
