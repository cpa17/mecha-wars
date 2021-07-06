package cardtests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.CardFunctions;

/**
 * Testclass.
 */
public class CardTests {

    /**
     * Tests if the movCount is only -1, 1, 2 or 3 .
     */
    @Test
    public void testMovCount() {
        Card[] testCards = new Card[84];

        testCards = CardFunctions.initDeck(testCards);

        for (int i = 0; i < 84; i += 1) {
            int x = testCards[i].getCardAttributeMovCount();
            assertTrue(-1 == x || 1 == x || 2 == x || 3 == x);
        }
    }

    /**
     * Tests if the names of the Buttons are the Strings of the enum "Name".
     */
    @Test
    public void testName() {
        Card[] testCards = new Card[84];

        testCards = CardFunctions.initDeck(testCards);

        for (int i = 0; i < 84; i += 1) {
            assertTrue(testCards[i].toString() == "1 Vor"
                    || testCards[i].toString() == "2 Vor"
                    || testCards[i].toString() == "3 Vor"
                    || testCards[i].toString() == "Rueckwaerts"
                    || testCards[i].toString() == "Rechtsdrehung"
                    || testCards[i].toString() == "Kehrtwendung"
                    || testCards[i].toString() == "Linksdrehung");
        }
    }

    /**
     * When initializing Card[] before shuffling the first 18 cards are mov1 (of type Name).
     * We have a total of 84 Cards. If the shuffle() function works
     * less than half of the first 18 cards (9) should be mov1 (of type Name)
     */
    @Test
    public void testCardShuffle1() {
        Card[] testCards = new Card[84];
        int internTest = 0;

        testCards = CardFunctions.initDeck(testCards);
        testCards = CardFunctions.shuffle(testCards);

        for (int i = 0; i < 18; i += 1) {
            if (testCards[i].toString() == "1 Vor") {
                internTest += 1;
            }
        }

        assertTrue(internTest < 9);        //statistically possible to be false

    }

    /**
     * Tests if at least one Card is different in testCardsShuffled[].
     * compared to testCardsUnshuffled[]
     */
    @Test
    public void testCardShuffle2() {
        Card[] testCardsUnshuffled = new Card[84];
        Card[] testCardsShuffled;
        boolean isEqual = true;

        testCardsUnshuffled =  CardFunctions.initDeck(testCardsUnshuffled);

        testCardsShuffled = CardFunctions.shuffle(testCardsUnshuffled);

        for (int i = 0; i < 84; i += 1) {
            if (testCardsUnshuffled[i]
                    != testCardsShuffled[i]) {
                isEqual = false;
            }
        }
        assertFalse(isEqual);
    }

}
