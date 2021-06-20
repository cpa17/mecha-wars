package cardTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.CardFunctions;
import htwk.mechawars.cards.Name;

public class CardTests {

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
    
    /**
     * When initializing Card[] before shuffling the first 18 cards are mov1 (of type Name).
     * We have a total of 84 Cards. If the shuffle() function works 
     * less than half of the first 18 cards (9) should be mov1 (of type Name)
     */
    @Test
    public void testCardShuffle1() {
        Card[] testCards = new Card[84];
        int iTest=0;
        
        testCards = CardFunctions.initDeck(testCards);
        testCards = CardFunctions.shuffle(testCards);
        
        for(int i = 0; i<18; i+=1) {
            if(testCards[i].getCardAttributeName() == Name.mov1) {
                iTest+=1;
            }
        }
        
        assertTrue(iTest<9);        //statistically possible to be false
        
    }
    
    /**
     * Tests if at least one Card is different in testCardsShuffled[] 
     * compared to testCardsUnshuffled[]
     */
    @Test
    public void testCardShuffle2() {
        Card[] testCardsUnshuffled = new Card[84];
        Card[] testCardsShuffled = new Card[84];
        boolean isEqual = true;
        
        testCardsUnshuffled =  CardFunctions.initDeck(testCardsUnshuffled);

        testCardsShuffled = CardFunctions.shuffle(testCardsUnshuffled);
        
        for(int i = 0; i < 84; i+=1) {
            if(testCardsUnshuffled[i].getCardAttributeName() != testCardsShuffled[i].getCardAttributeName()) {
                isEqual = false;
            }
        }
        assertFalse(isEqual);
    }
    
}
