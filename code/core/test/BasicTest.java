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
    @Test
    public void testMovCount() {
        Card[] testCards = new Card[84];
        
        testCards = CardFunctions.initDeck(testCards);
        
        for(int i=0; i<84; i+=1) {
            int x = testCards[i].getCardAttributeMovCount();
            assertTrue(-1 == x || 1 == x || 2 == x || 3 == x);
        }
    }
    
    /**
     * Tests if the names of the Buttons are the Strings of the enum "Name"
     */
    @Test
    public void testName() {
        Card[] testCards = new Card[84];
        
        testCards = CardFunctions.initDeck(testCards);
        
        for(int i = 0; i < 84; i+=1) {
            assertTrue(testCards[i].getCardAttributeName().get_Name() == "1 Vor" ||
                       testCards[i].getCardAttributeName().get_Name() == "2 Vor" ||
                       testCards[i].getCardAttributeName().get_Name() == "3 Vor" ||
                       testCards[i].getCardAttributeName().get_Name() == "Rueckwaerts" ||
                       testCards[i].getCardAttributeName().get_Name() == "Rechtsdrehung" ||
                       testCards[i].getCardAttributeName().get_Name() == "Kehrtwendung" ||
                       testCards[i].getCardAttributeName().get_Name() == "Linksdrehung" );
        }
    }
    
    @Test
    public void testCardShuffle() {
        Card[] testCards = new Card[84];
        int iTest=0;
        
        testCards = CardFunctions.initDeck(testCards);
        testCards = CardFunctions.shuffle(testCards);
        
        for(int i = 0; i<18; i+=1) {
            if(testCards[i].getCardAttributeName().get_Name()=="1 Vor") {
                iTest+=1;
            }
        }
        
        assertTrue(iTest<9);
        
    }
}
