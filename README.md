## Summary

The following is an incomplete application who's function is to compare two poker hands.
In the `PokerHand` class you will find an unimplemented method called `int compareTo(PokerHand opponentHand)`.

Please complete such that the method returns the following results;

- `HandResult.WIN.comparatorValue` player's hand beats opponent's hand
- `HandResult.LOSS.comparatorValue` opponent's hand wins
- `HandResult.TIE.comparatorValue` tied result (split pot)

Hands will be ranked according to the rules of [Texas Hold'em](https://en.wikipedia.org/wiki/Texas_hold_%27em).

The `PokerHand` class is constructed as follows;

```java
PokerHand hand = new PokerHand("4S 2H 5C JD TD");
```

- Each card is made up of two characters, where
    - The first is the value of the card: `2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce)`
    - The second is the suit: `S(pades), H(earts), D(iamonds), C(lubs)`
- cards are separated by a space

- Aces are the highest card in the deck (14), so `"AS KH QC JD TD"` _is_ a straight, but
  `"AS 2H 3C 4D 5D"` _is not_.

### Tests
Included is a suite of unit tests that should verify the correctness of the solution.
We do not expect there to be a test for every possible combination of poker hands but you are welcome to add additional test
cases as long as you do not remove or modify any of those provided.

The tests can be run by executing `./gradlew test` (Mac/Linux) from the project's root directory.

## Guidelines
The game can be implemented using any combination of the following languages:
- Java
- Kotlin
- Groovy

You are welcome to add any 3rd party libraries/frameworks. If you choose Kotlin or Groovy, you'll need to modify the
build file to support compilation of these languages. It is recommended you use at least JDK version 8.

The goal is to pass the test suite. If there isn't a test for a particular case, e.g. an invalid string being passed to the `PokerHand` constructor, you may ignore it.

### GitHub
- Clone or download this repo (do not fork it)
- Push your solution to a private GitHub repo in your account
- When you are happy that your solution is complete, add your eCurrency contact as a collaborator to this repo, so we can access.

## Evaluation
While it would be preferable to get a completed solution i.e. which passes all of the included test cases,
we're more interested in the quality of the solution rather than completeness. In other words, a high quality solution with some minor emissions
will receive more credit than a complete, but low-quality solution.