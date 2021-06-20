package htwk.mechawars.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Includes the functions, that are needed for working with an cardDeck
 */
public class CardFunctions{

    /**
     * Fill the empty cardDeck-Array with:
     * # 18 Mov1, TurnR, TurnL
     * # 12 Mov2
     * # 6  Mov3, MovB, TurnU   .
     * 
     * @param cardDeck
     * @return The initial cardDeck
     */
    public static Card[] initDeck(Card[] cardDeck) {
        for (int i = 0; i < 18; i += 1) {
            cardDeck[i]      = new Card(Name.mov1, Type.mov, (byte) 1 );
            cardDeck[i + 18] = new Card(Name.turnR, Type.turn, (byte) 1);
            cardDeck[i + 36] = new Card(Name.turnL, Type.turn, (byte) 3);
        }
        for (int i = 54; i < 60; i += 1) {
            cardDeck[i]      = new Card(Name.mov3, Type.mov, (byte) 3);
            cardDeck[i + 6]  = new Card(Name.movB, Type.mov, (byte) -1);
            cardDeck[i + 12] = new Card(Name.turnU, Type.turn, (byte) 2);
        }
        for (int i = 72; i < 84; i += 1) {
            cardDeck[i]      = new Card(Name.mov2, Type.mov, (byte) 2);
        }
        return cardDeck;
    }
 
    /**
     * Shuffles the cards, which were created before.
     * 
     * @param oDeck -> includes the cards to shuffle
     * @return Array of cards, that are shuffled
     */
    public static Card[] shuffle(Card[] oDeck) {
        List<Card> deck = new ArrayList<>(Arrays.asList(oDeck));
        List<Card> shuffled = new ArrayList<>();

        Collections.shuffle(deck);
        shuffled.addAll(deck);
    
        return shuffled.toArray(new Card[shuffled.size()]);
    }
}