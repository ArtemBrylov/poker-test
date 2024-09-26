package com.pokerexample.poker;

import com.pokerexample.poker.entity.Card;
import com.pokerexample.poker.entity.CardValue;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.pokerexample.util.PokerUtil.getSameCardValues;
import static com.pokerexample.util.PokerUtil.removePair;

public class PokerHandComparator implements Comparator<PokerHand> {

    public int compare(PokerHand hand1, PokerHand hand2) {
        HandAnalyser handAnalyser = new HandAnalyser(hand1);
        HandValuesCalculator calculator = new HandValuesCalculator();

        int handOneScore = calculator.calculateHandCombinationsScore(hand1);
        int handTwoScore = calculator.calculateHandCombinationsScore(hand2);

        if (handOneScore == handTwoScore) {
            if (handAnalyser.hasFourOfAKind()) {
                return compareHighestFourOfAKinds(hand1, hand2);
            } else if (handAnalyser.hasTwoPairs()) {
                return compareHighestTwoPairs(hand1, hand2);
            } else if (handAnalyser.hasPair() || handAnalyser.hasFullHouse()) {
                removePair(hand1.getCards());
                removePair(hand2.getCards());
                return compareKickers(hand1, hand2);
            } else {
                return compareKickers(hand1, hand2);
            }
        }

        if (handOneScore > handTwoScore) {
            return HandResult.WIN.comparatorValue;
        } else if (handOneScore < handTwoScore) {
            return HandResult.LOSS.comparatorValue;
        } else {
            return HandResult.TIE.comparatorValue;
        }
    }

    public int compareKickers(PokerHand hand1, PokerHand hand2) {
        List<Card> sortedCards = hand1.getCards().stream()
                .sorted(Comparator.comparing(Card::getValue).reversed())
                .collect(Collectors.toList());

        List<Card> sortedOpponentCards = hand2.getCards().stream()
                .sorted(Comparator.comparing(Card::getValue).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < sortedCards.size(); i++) {
            int comparison = Integer.compare(
                    sortedCards.get(i).getValue().ordinal(),
                    sortedOpponentCards.get(i).getValue().ordinal()
            );
            if (comparison > 0) {
                return HandResult.WIN.comparatorValue;
            } else if (comparison < 0) {
                return HandResult.LOSS.comparatorValue;
            }
        }
        return HandResult.TIE.comparatorValue;
    }

    public int compareHighestFourOfAKinds(PokerHand hand1, PokerHand hand2) {
        CardValue highestCard = getSameCardValues(hand1.getCards(), 4);
        CardValue opponentHighestCard = getSameCardValues(hand2.getCards(), 4);

        if (CardValue.fromChar(highestCard.getSymbol()).ordinal() >
                CardValue.fromChar(opponentHighestCard.getSymbol()).ordinal()) {
            return HandResult.WIN.comparatorValue;
        } else {
            return HandResult.LOSS.comparatorValue;
        }
    }

    public int compareHighestTwoPairs(PokerHand hand1, PokerHand hand2) {
        CardValue firstPairValue = getSameCardValues(hand1.getCards(), 2);
        CardValue firstOpponentPairValue = getSameCardValues(hand2.getCards(), 2);
        removePair(hand1.getCards());
        removePair(hand2.getCards());

        CardValue secondPairValue = getSameCardValues(hand1.getCards(), 2);
        CardValue secondOpponentPairValue = getSameCardValues(hand2.getCards(), 2);
        removePair(hand1.getCards());
        removePair(hand2.getCards());

        int pairSum = CardValue.fromChar(firstPairValue.getSymbol()).ordinal() +
                CardValue.fromChar(secondPairValue.getSymbol()).ordinal();
        int opponentPairSum = CardValue.fromChar(firstOpponentPairValue.getSymbol()).ordinal() +
                CardValue.fromChar(secondOpponentPairValue.getSymbol()).ordinal();

        if (pairSum > opponentPairSum) {
            return HandResult.WIN.comparatorValue;
        } else if (pairSum < opponentPairSum) {
            return HandResult.LOSS.comparatorValue;
        } else {
            return compareKickers(hand1, hand2);
        }
    }
}
