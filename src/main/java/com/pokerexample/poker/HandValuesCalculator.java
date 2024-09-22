package com.pokerexample.poker;

import static com.pokerexample.poker.HandAnalyser.*;
import static com.pokerexample.util.PokerUtil.*;

public class HandValuesCalculator {
    private HandValuesCalculator() {
    }

    public static int calculateHandCombinations(PokerHand hand) {
        int score = 0;
        if (hasRoyalFlush(hand)) {
            score += 100;
        } else if (hasStraightFlush(hand)) {
            score += 90;
        } else if (hasFourOfAKind(hand)) {
            score += 80;
        } else if (hasFullHouse(hand)) {
            score += 70;
        } else if (hasFlush(hand)) {
            score += 60;
        } else if (hasStraight(hand)) {
            score += 50;
        } else if (hasThreeOfAKind(hand)) {
            score += 40;
        } else if (hasTwoPairs(hand)) {
            score += 30;
        } else if (hasPair(hand)) {
            score += 20;
        }
        return score;
    }

    public static int calculateKicker(PokerHand hand, PokerHand opponentHand) {
        String cards = hand.getCards();
        String opponentCards = opponentHand.getCards();

        for (int i = 0; i < hand.getCards().split(" ").length; i++) {
            String highestCard = getHighCard(cards);
            String highestOpponentCard = getHighCard(opponentCards);

            if (highestCard.charAt(0) > highestOpponentCard.charAt(0)) {
                return HandResult.WIN.comparatorValue;
            }
            if (highestCard.charAt(0) < highestOpponentCard.charAt(0)) {
                return HandResult.LOSS.comparatorValue;
            }

            cards = removeOneCardByValue(cards.split(" "), highestCard.charAt(0));
            opponentCards = removeOneCardByValue(opponentCards.split(" "), highestOpponentCard.charAt(0));
        }

        return HandResult.TIE.comparatorValue;
    }

    public static void calculateHighestFourOfAKind(PokerHand hand, PokerHand opponentHand) {
        Character highestCard = getSameCardValues(hand, 4);
        Character opponentHighestCard = getSameCardValues(opponentHand, 4);

        if (CardValue.fromChar(highestCard).ordinal() > CardValue.fromChar(opponentHighestCard).ordinal()) {
            hand.setScore(hand.getScore() + 5);
        } else {
            opponentHand.setScore(opponentHand.getScore() + 5);
        }
    }

    public static void calculateHighestTwoPairs(PokerHand hand, PokerHand opponentHand) {
        Character firstPairValue = getSameCardValues(hand, 2);
        Character firstOpponentPairValue = getSameCardValues(opponentHand, 2);
        PokerHand handWithoutFirstPair = removePair(hand);
        PokerHand handWithoutFirstPairOpponent = removePair(opponentHand);

        Character secondPairValue = getSameCardValues(handWithoutFirstPair, 2);
        Character secondOpponentPairValue = getSameCardValues(handWithoutFirstPairOpponent, 2);

        int pairSum = CardValue.fromChar(firstPairValue).ordinal() + CardValue.fromChar(secondPairValue).ordinal();
        int opponentPairSum = CardValue.fromChar(firstOpponentPairValue).ordinal() + CardValue.fromChar(secondOpponentPairValue).ordinal();

        if (pairSum > opponentPairSum) {
            hand.setScore(hand.getScore() + 5);
        } else {
            opponentHand.setScore(opponentHand.getScore() + 5);
        }
    }
}
