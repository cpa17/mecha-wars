package cardtests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.CardFunctions;

/**
 * Testclass.
 */
public class CardTests {
    
    private ArrayList<Card> testCards;

    /**
     * Tests if the movCount is only -1, 1, 2 or 3 .
     */
    @Test
    public void testMovCount() {
        testCards = CardFunctions.initDeck(testCards);
        
        for (int i = 0; i < 84; i += 1) {
            int x = testCards.get(i).getCardAttributeMovCount();
            assertTrue(-1 == x || 1 == x || 2 == x || 3 == x);
        }
    }

    /**
     * Tests if the names of the Buttons are the Strings of the enum "Name".
     */
    @Test
    public void testName() {
        testCards = CardFunctions.initDeck(testCards);

        for (int i = 0; i < 84; i += 1) {
            assertTrue(testCards.get(i).toString() == "1 Vor"
                    || testCards.get(i).toString() == "2 Vor"
                    || testCards.get(i).toString() == "3 Vor"
                    || testCards.get(i).toString() == "Rueckwaerts"
                    || testCards.get(i).toString() == "Rechtsdrehung"
                    || testCards.get(i).toString() == "Kehrtwendung"
                    || testCards.get(i).toString() == "Linksdrehung");
        }
    }

    /**
     * Tests if at least one Card is different in testCardsShuffled[].
     * compared to testCardsUnshuffled[]
     */
    @Test
    public void testCardShuffle() {
        ArrayList<Card> testCardsUnshuffled = CardFunctions.initDeck(testCards);
        ArrayList<Card> testCardsShuffled;
        boolean isEqual = true;

        testCardsShuffled = CardFunctions.shuffle(testCardsUnshuffled);

        for (int i = 0; i < 84; i += 1) {
            if (testCardsUnshuffled.get(i)
                    != testCardsShuffled.get(i)) {
                isEqual = false;
            }
        }
        assertFalse(isEqual);
    }

    /**
     * Test the amount of Turn-Left-Cards in the Array, which have to be 18.
     */
    @Test
    public void testCardCount() {
        testCards = CardFunctions.initDeck(testCards);
        int counter = 0;

        for (int i = 0; i < 84; i += 1) {
            if (testCards.get(i).getCardAttributeType().toString() == "Linksdrehung") {
                counter += 1;
            }
        }
        assertTrue(counter < 18);
    }


    /**
     * Test, that show, that the priority of cards are correct.
     */
    @Test
    public void testPriority() {
        testCards = CardFunctions.initDeck(testCards);
        boolean prio0 = false;
        boolean prio1 = false;
        boolean prio2 = false;
        boolean prio3 = false;

        //for (int x = 0; x < 84; x += 1) {
        //    System.out.println( testCards[x].toString() + "  "
        //          + testCards[x].getCardAttributePriority());
        //} -> Manuel Test of Priority

        for (int i = 0; i < 84; i += 1) {
            System.out.println("e" + i);
            if (testCards.get(i).toString() == "Kehrtwendung") {
                System.out.println("1 ja");
                if (testCards.get(i).getCardAttributePriority() == 70) {
                    prio0 = true;
                    System.out.println("2 Ja");
                }
            }
            if (testCards.get(i).getCardAttributePriority() == 410) {
                System.out.println("Hallo Prio");
                if (testCards.get(i).toString() == "Linksdrehung") {
                    prio1 = true;
                    System.out.println("1");
                }
            }
            if (testCards.get(i).getCardAttributePriority() == 510
                    && testCards.get(i).toString() == "1 Vor") {
                prio2 = true;
                System.out.println("2");
            }
            if (testCards.get(i).getCardAttributePriority() == 80
                    && testCards.get(i).toString() == "Rechtsdrehung") {
                prio3 = true;
                System.out.println("3");
            }
        }
        assertFalse(prio0);
        assertTrue(prio1);
        assertTrue(prio2);
        assertTrue(prio3);
    }
}
