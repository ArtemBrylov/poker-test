package com.pokerexample.poker;

import java.util.Objects;

import static com.pokerexample.poker.HandValuesCalculator.*;
import static com.pokerexample.poker.HandAnalyser.*;
import static com.pokerexample.poker.HandValuesCalculator.calculateKicker;
import static com.pokerexample.util.PokerUtil.*;

public class PokerHand implements Comparable<PokerHand> {
    private final String cards;
    private int score = 0;

    public PokerHand(String fiveCards) {
        this.cards = fiveCards;
        score = calculateHandCombinations(this);
    }

    public String getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(PokerHand opponentHand) {
        if (this.score == opponentHand.score) {
            if (hasFourOfAKind(this)) {
                calculateHighestFourOfAKind(this, opponentHand);
            } else if (hasTwoPairs(this)) {
                calculateHighestTwoPairs(this, opponentHand);
            } else if (hasPair(this) || hasFullHouse(this)) {
                return calculateKicker(removePair(this), removePair(opponentHand));
            } else {
                return calculateKicker(this, opponentHand);
            }
        }

        if (this.score > opponentHand.getScore()) {
            return HandResult.WIN.comparatorValue;
        } else if (this.score < opponentHand.getScore()) {
            return HandResult.LOSS.comparatorValue;
        } else {
            return HandResult.TIE.comparatorValue;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand pokerHand = (PokerHand) o;
        return score == pokerHand.score && Objects.equals(cards, pokerHand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, score);
    }
}