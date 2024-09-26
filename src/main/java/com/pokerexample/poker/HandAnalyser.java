package com.pokerexample.poker;

import com.pokerexample.poker.entity.Card;
import com.pokerexample.poker.entity.CardSuit;
import com.pokerexample.poker.entity.CardValue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pokerexample.util.PokerUtil.*;

public class HandAnalyser {
    private final PokerHand hand;

    public HandAnalyser(PokerHand hand) {
        this.hand = hand;
    }

    public boolean hasRoyalFlush() {
        return hasSameSuit() && isHighestStraight();
    }

    public boolean hasStraightFlush() {
        return hasSameSuit() && hasStraight();
    }

    public boolean hasFourOfAKind() {
        return hasSameCardValues(hand.getCards(), 4);
    }

    public boolean hasFullHouse() {
        return hasThreeOfAKind() && hasPair();
    }

    public boolean hasFlush() {
        return hasSameSuit();
    }

    public boolean hasStraight() {
        return hasValues(hand.getCards(), List.of(CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE, CardValue.SIX))
                || hasValues(hand.getCards(), List.of(CardValue.THREE, CardValue.FOUR, CardValue.FIVE, CardValue.SIX, CardValue.SEVEN))
                || hasValues(hand.getCards(), List.of(CardValue.FOUR, CardValue.FIVE, CardValue.SIX, CardValue.SEVEN, CardValue.EIGHT))
                || hasValues(hand.getCards(), List.of(CardValue.FIVE, CardValue.SIX, CardValue.SEVEN, CardValue.EIGHT, CardValue.NINE))
                || hasValues(hand.getCards(), List.of(CardValue.SIX, CardValue.SEVEN, CardValue.EIGHT, CardValue.NINE, CardValue.TEN))
                || hasValues(hand.getCards(), List.of(CardValue.SEVEN, CardValue.EIGHT, CardValue.NINE, CardValue.TEN, CardValue.JACK))
                || hasValues(hand.getCards(), List.of(CardValue.EIGHT, CardValue.NINE, CardValue.TEN, CardValue.JACK, CardValue.QUEEN))
                || hasValues(hand.getCards(), List.of(CardValue.NINE, CardValue.TEN, CardValue.JACK, CardValue.QUEEN, CardValue.KING))
                || hasValues(hand.getCards(), List.of(CardValue.TEN, CardValue.JACK, CardValue.QUEEN, CardValue.KING, CardValue.ACE))
                || hasValues(hand.getCards(), List.of(CardValue.ACE, CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE));
    }

    public boolean hasThreeOfAKind() {
        return hasSameCardValues(hand.getCards(), 3);
    }

    public boolean hasTwoPairs() {
        Map<CardValue, Long> cardCountMap = hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        long pairCount = cardCountMap.values().stream()
                .filter(count -> count == 2)
                .count();
        return pairCount == 2;
    }

    public boolean hasPair() {
        return hasSameCardValues(hand.getCards(), 2);
    }

    private boolean isHighestStraight() {
        return hasValues(hand.getCards(), List.of(CardValue.TEN, CardValue.JACK, CardValue.QUEEN, CardValue.KING, CardValue.ACE));
    }

    private boolean hasSameSuit() {
        return amountOfCardSuits(hand.getCards(), CardSuit.SPADES) == 5
                || amountOfCardSuits(hand.getCards(), CardSuit.HEARTS) == 5
                || amountOfCardSuits(hand.getCards(), CardSuit.DIAMONDS) == 5
                || amountOfCardSuits(hand.getCards(), CardSuit.CLUBS) == 5;
    }
}
