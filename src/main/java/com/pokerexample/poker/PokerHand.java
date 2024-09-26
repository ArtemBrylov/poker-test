package com.pokerexample.poker;

import com.pokerexample.poker.entity.Card;
import com.pokerexample.poker.entity.CardSuit;
import com.pokerexample.poker.entity.CardValue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private final List<Card> cards;

    public PokerHand(String hand) {
        this.cards = parseHand(hand);
    }

    public List<Card> getCards() {
        return cards;
    }

    private List<Card> parseHand(String hand) {
        return Arrays.stream(hand.split(" "))
                .map(card -> new Card(CardValue.fromChar(card.charAt(0)),
                        CardSuit.fromChar(card.charAt(1))))
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(PokerHand opponentHand) {
        PokerHandComparator comparator = new PokerHandComparator();
        return comparator.compare(this, opponentHand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand pokerHand = (PokerHand) o;
        return Objects.equals(cards, pokerHand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }

    @Override
    public String toString() {
        return "PokerHand{" +
                "cards=" + cards +
                '}';
    }
}