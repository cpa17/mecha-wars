package htwk.mechawars.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Includes the functions, that are needed for working with an cardDeck.
 */
public class CardFunctions {

    /**
     * Fill the empty cardDeck-Array with:
     * # 18 Mov1, TurnR, TurnL
     * # 12 Mov2
     * # 6  Mov3, MovB, TurnU   . Also their priority this function will give them.
     * 
     * @param cardDeck  -> Array, that should be initialized
     * @return The initial cardDeck
     */
    public static Card[] initDeck(Card[] cardDeck) {
        byte x = 10;
        // List<Card> deck = new ArrayList<>(Arrays.asList(cardDeck));
        // First 6 cards (U-Turn)
        for (int i = 0; i < 6; i += 1) {
            //MUSTER : deck.add(new Card(Type.turn, (byte) 2, x));
            cardDeck[i]      = new Card(Type.turn, (byte) 2, x);
            x += 10;
        }
        // x = 70;
        // Card 7, 9, 11 .. 41 (Left-Turn)
        for (int i = 6; i < 40; i += 2) {
            cardDeck[i]      = new Card(Type.turn, (byte) 3, x);
            x += 20;
        }
        x = 80;
        // Card 8, 10 .. 42 (Right-Turn)
        for (int i = 7; i < 41; i += 2) {
            cardDeck[i]      = new Card(Type.turn, (byte) 1, x);
            x += 20;
        }
        // x = 430;
        // Card 43 .. 48
        for (int i = 42; i < 48; i += 1) {
            cardDeck[i]      = new Card(Type.mov, (byte) -1, x);
            x += 10; 
        }
        // x = 490
        // Card 49 .. 66
        for (int i = 49; i < 66; i += 1) {
            cardDeck[i]      = new Card(Type.mov, (byte) 1, x);
            x += 10;
        }
        // x = 670
        // Card 67 .. 78
        for (int i = 66; i < 78; i += 1) {
            cardDeck[i]      = new Card(Type.mov, (byte) 2, x);
            x += 10;
        }
        // x = 490
        // Card 79 .. 84
        for (int i = 78; i < 84; i += 1) {
            cardDeck[i]      = new Card(Type.mov, (byte) 3, x);
            x += 10;
        }
        
        //-------------------------------------------------------------
        /*
        for (int i = 0; i < 18; i += 1) {
            cardDeck[i]      = new Card(Type.mov, (byte) 1, (byte) 0);
            cardDeck[i + 18] = new Card(Type.turn, (byte) 1, (byte) 0);
            cardDeck[i + 36] = new Card(Type.turn, (byte) 3, (byte) 0);
        }
        for (int i = 54; i < 60; i += 1) {
            cardDeck[i]      = new Card(Type.mov, (byte) 3, (byte) 0);
            cardDeck[i + 6]  = new Card(Type.mov, (byte) -1, (byte) 0);
            cardDeck[i + 12] = new Card(Type.turn, (byte) 2, (byte) 0);
        }
        for (int i = 72; i < 84; i += 1) {
            cardDeck[i]      = new Card(Type.mov, (byte) 2, (byte) 0);
        }
        */
        //-------------------------------------------------------------
        return cardDeck;
    }
 
    /**
     * Shuffles the cards, which were created before.
     * 
     * @param originalDeck -> includes the cards to shuffle
     * @return Array of cards, that are shuffled
     */
    public static Card[] shuffle(Card[] originalDeck) {
        List<Card> deck = new ArrayList<>(Arrays.asList(originalDeck));
        List<Card> shuffled = new ArrayList<>();

        Collections.shuffle(deck);
        shuffled.addAll(deck);
    
        return shuffled.toArray(new Card[shuffled.size()]);
    }
}