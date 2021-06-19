import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import htwk.mechawars.cards.Card;

import htwk.mechawars.cards.CardFunctions;

class BasicTest {
    @Test
    public void testBasic() {
        assertEquals(2, 1 + 1);
    }
    
    /**
     * Tests if the movCount is only -1, 1, 2 or 3
     */
    public void kartenAnzahlTest() {
        Card[] testCards = new Card[84];
        
        testCards = CardFunctions.initDeck(testCards);
        
        for(int i=0; i<84; i+=1) {
            int x = testCards[i].getCardAttributeMovCount();
            assertEquals(-1, x, "-1 Stimmt" + (i+1) + ". Karte");
            assertEquals(1, x, "1 Stimmt" + (i+1) + ". Karte");
            assertEquals(2, x, "2 Stimmt" + (i+1) + ". Karte");
            assertEquals(3, x, "3 Stimmt" + (i+1) + ". Karte");
        }
    }
    
    /**
     * Tests if the names of the Buttons are the Strings of the enum "Name"
     */
    public void testName() {
        Card[] testCards = new Card[84];
        for (int i = 0; i < 84; i+=1) {
            assertTrue(testCards[i].getCardAttributeName().get_Name() == "1 Vor" ||
                       testCards[i].getCardAttributeName().get_Name() == "2 Vor" ||
                       testCards[i].getCardAttributeName().get_Name() == "3 Vor" ||
                       testCards[i].getCardAttributeName().get_Name() == "Rueckwaerts" ||
                       testCards[i].getCardAttributeName().get_Name() == "Rechtsdrehung" ||
                       testCards[i].getCardAttributeName().get_Name() == "Kehrtwendung" ||
                       testCards[i].getCardAttributeName().get_Name() == "Linksdrehung" );
        }
    }
}
