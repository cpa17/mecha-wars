package cardtests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import htwk.mechawars.cards.Deck;

/**
 * Testclass.
 */
public class CardTests {

    /**
     * Tests if the movCount is only -1, 1, 2 or 3 .
     */
    @Test
    public void testMovCount() {
        Deck testDeckMovCount = new Deck();
        
        for (int i = 0; i < 84; i += 1) {
            int x = testDeckMovCount.getDeck().get(i).getCardAttributeMovCount();
            assertTrue(-1 == x || 1 == x || 2 == x || 3 == x);
        }
    }

    /**
     * Tests if the names of the Buttons are the Strings of the enum "Name".
     */
    @Test
    public void testName() {
        Deck testDeckNames = new Deck();

        for (int i = 0; i < 84; i += 1) {
            assertTrue(testDeckNames.getDeck().get(i).toString() == "1 Vor"
                    || testDeckNames.getDeck().get(i).toString() == "2 Vor"
                    || testDeckNames.getDeck().get(i).toString() == "3 Vor"
                    || testDeckNames.getDeck().get(i).toString() == "Rueckwaerts"
                    || testDeckNames.getDeck().get(i).toString() == "Rechtsdrehung"
                    || testDeckNames.getDeck().get(i).toString() == "Kehrtwendung"
                    || testDeckNames.getDeck().get(i).toString() == "Linksdrehung");
        }
    }

    /**
     * Tests if at least one Card is different in testCardsShuffled[].
     * compared to testCardsUnshuffled[]
     */
    @Test
    public void testCardShuffle() {
        Deck testDeckShuffled = new Deck();
        testDeckShuffled.shuffle();
        Deck testDeckUnshuffled = new Deck();
        
        boolean isEqual = true;

        for (int i = 0; i < 84; i += 1) {
            if (testDeckUnshuffled.getDeck().get(i)
                    != testDeckShuffled.getDeck().get(i)) {
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
        Deck testDeckCardCount = new Deck();
        int counter = 0;

        for (int i = 0; i < 84; i += 1) {
            if (testDeckCardCount.getDeck().get(i).getCardAttributeType().toString() 
                    == "Linksdrehung") {
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
        Deck testDeck = new Deck();
        boolean prio0 = false;
        boolean prio1 = false;
        boolean prio2 = false;
        boolean prio3 = false;

        for (int i = 0; i < 84; i += 1) {
            System.out.println("e" + i);
            if (testDeck.getDeck().get(i).toString() == "Kehrtwendung") {
                System.out.println("1 ja");
                if (testDeck.getDeck().get(i).getCardAttributePriority() == 70) {
                    prio0 = true;
                    System.out.println("2 Ja");
                }
            }
            if (testDeck.getDeck().get(i).getCardAttributePriority() == 410) {
                System.out.println("Hallo Prio");
                if (testDeck.getDeck().get(i).toString() == "Linksdrehung") {
                    prio1 = true;
                    System.out.println("1");
                }
            }
            if (testDeck.getDeck().get(i).getCardAttributePriority() == 510
                    && testDeck.getDeck().get(i).toString() == "1 Vor") {
                prio2 = true;
                System.out.println("2");
            }
            if (testDeck.getDeck().get(i).getCardAttributePriority() == 80
                    && testDeck.getDeck().get(i).toString() == "Rechtsdrehung") {
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
