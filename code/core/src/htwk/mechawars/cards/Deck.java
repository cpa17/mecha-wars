package htwk.mechawars.cards;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Includes the functions, that are needed for working with an cardDeck.
 */
public class Deck {
    
    ArrayList<Card> deck = new ArrayList<>();
    
    /**
     * Constructor of a complete Deck.
     */
    public Deck() {
        initDeck(deck);
    }

    /**
     * 
     * @return
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Fill the empty cardDeck-Array with:
     * # 18 Mov1, TurnR, TurnL
     * # 12 Mov2
     * # 6  Mov3, MovB, TurnU   . Also their priority this function will give them.
     *
     * @return The initial cardDeck
     */
    public ArrayList<Card> initDeck(ArrayList<Card> cardDeck) {

        cardDeck.clear();   //clear Arraylist completely for initialization

        int priority = 10;

        // 6 Cards: U-Turn / priority 10-60
        for (int i = 0; i < 6; i += 1) {
            cardDeck.add(new Card(Type.turn, (byte) 2, priority));
            priority += 10;
        }

        // 18 Cards: Left-Turn & Right-Turn / priority 70-420
        for (int i = 0; i < 18; i += 1) {
            cardDeck.add(new Card(Type.turn, (byte) 3, priority));
            priority += 10;
            cardDeck.add(new Card(Type.turn, (byte) 1, priority));
            priority += 10;
        }

        // 6 Cards: Backup / priority 430-480
        for (int i = 0; i < 6; i += 1) {
            cardDeck.add(new Card(Type.mov, (byte) -1, priority));
            priority += 10;
        }

        // 18 Cards: Move 1 / priority 490-660
        for (int i = 0; i < 18; i += 1) {
            cardDeck.add(new Card(Type.mov, (byte) 1, priority));
            priority += 10;
        }

        // 12 Cards: Move 2 / priority 670-780
        for (int i = 0; i < 12; i += 1) {
            cardDeck.add(new Card(Type.mov, (byte) 2, priority));
            priority += 10;
        }

        // 6 Cards: Move 3 / priority 790-840
        for (int i = 0; i < 6; i += 1) {
            cardDeck.add(new Card(Type.mov, (byte) 3, priority));
            priority += 10;
        }

        return cardDeck;
    }

    /**
     * Shuffles the cards, which were created before.
     *
     * @param cardDeck -> includes the cards to shuffle
     * @return List of cards, that are shuffled
     */
    public ArrayList<Card> shuffle(ArrayList<Card> cardDeck) {
        // Function from Java to shuffle the List
        Collections.shuffle(cardDeck);

        return cardDeck;
    }
}
