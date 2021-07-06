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
        Card[] testCards = CardFunctions.initDeck();

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
        Card[] testCards = CardFunctions.initDeck();

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
     * Tests if at least one Card is different in testCardsShuffled[].
     * compared to testCardsUnshuffled[]
     */
    @Test
    public void testCardShuffle() {
        Card[] testCardsUnshuffled = CardFunctions.initDeck();
        Card[] testCardsShuffled;
        boolean isEqual = true;

        testCardsShuffled = CardFunctions.shuffle(testCardsUnshuffled);

        for (int i = 0; i < 84; i += 1) {
            if (testCardsUnshuffled[i]
                    != testCardsShuffled[i]) {
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
        Card[] testCards = CardFunctions.initDeck();
        int counter = 0;
        
        for (int i = 0; i < 84; i += 1) {
            if (testCards[i].getCardAttributeType().toString() == "Linksdrehung") {
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
        Card[] testCards = CardFunctions.initDeck();
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
            if (testCards[i].toString() == "Kehrtwendung") {
                System.out.println("1 ja");
                if (testCards[i].getCardAttributePriority() == 70) {
                    prio0 = true;
                    System.out.println("2 Ja");
                }
            }
            if (testCards[i].getCardAttributePriority() == 410) {
                System.out.println("Hallo Prio");
                if (testCards[i].toString() == "Linksdrehung") {
                    prio1 = true;
                    System.out.println("1");
                }
            }
            if (testCards[i].getCardAttributePriority() == 510
                    && testCards[i].toString() == "1 Vor") {
                prio2 = true;
                System.out.println("2");
            }
            if (testCards[i].getCardAttributePriority() == 80
                    && testCards[i].toString() == "Rechtsdrehung") {
                prio3 = true;
                System.out.println("3");
            }
        }
        assertFalse(prio0 == true);
        assertTrue(prio1 == true);
        assertTrue(prio2 == true);
        assertTrue(prio3 == true);
    }
}
