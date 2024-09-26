package com.pokerexample.poker;

public class HandValuesCalculator {
    public int calculateHandCombinationsScore(PokerHand hand) {
        HandAnalyser handAnalyser = new HandAnalyser(hand);
        int score = 0;
        if (handAnalyser.hasRoyalFlush()) {
            score += 100;
        } else if (handAnalyser.hasStraightFlush()) {
            score += 90;
        } else if (handAnalyser.hasFourOfAKind()) {
            score += 80;
        } else if (handAnalyser.hasFullHouse()) {
            score += 70;
        } else if (handAnalyser.hasFlush()) {
            score += 60;
        } else if (handAnalyser.hasStraight()) {
            score += 50;
        } else if (handAnalyser.hasThreeOfAKind()) {
            score += 40;
        } else if (handAnalyser.hasTwoPairs()) {
            score += 30;
        } else if (handAnalyser.hasPair()) {
            score += 20;
        }
        return score;
    }
}
