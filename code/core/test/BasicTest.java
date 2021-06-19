import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import htwk.mechawars.cards.Card;

import htwk.mechawars.cards.CardFunctions;

class BasicTest {
    @Test
    public void testBasic() {
        assertEquals(2, 1 + 1);
    }
    
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
}
