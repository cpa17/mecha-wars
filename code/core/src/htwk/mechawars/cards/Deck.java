package htwk.mechawars.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Includes the functions, that are needed for working with an cardDeck.
 */
public class Deck {
    
    ArrayList<Card> deck = new ArrayList<>();
    

    
    /**
     * Constructor of a complete Deck.
     */
    public Deck() {
        initDeck();
    }

    /**
     * Getter-Function for the ArrayList<\Card\> deck.
     * 
     * @return the hole deck (which is an ArrayList of Cards) 
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Fill the empty cardDeck-Array with:
     * # 18 Mov1, TurnR, TurnL
     * # 12 Mov2
     * # 6  Mov3, MovB, TurnU   . Also their priority this function will give them.
     */
    public void initDeck() {

        int playerNumber = -1;
        deck.clear();   //clear Arraylist completely for initialization

        int priority = 10;

        // 6 Cards: U-Turn / priority 10-60
        for (int i = 0; i < 6; i += 1) {
            deck.add(new Card(Type.turn, (byte) 2, priority, playerNumber));
            priority += 10;
        }

        // 18 Cards: Left-Turn & Right-Turn / priority 70-420
        for (int i = 0; i < 18; i += 1) {
            deck.add(new Card(Type.turn, (byte) 3, priority, playerNumber));
            priority += 10;
            deck.add(new Card(Type.turn, (byte) 1, priority, playerNumber));
            priority += 10;
        }

        // 6 Cards: Backup / priority 430-480
        for (int i = 0; i < 6; i += 1) {
            deck.add(new Card(Type.mov, (byte) -1, priority, playerNumber));
            priority += 10;
        }

        // 18 Cards: Move 1 / priority 490-660
        for (int i = 0; i < 18; i += 1) {
            deck.add(new Card(Type.mov, (byte) 1, priority, playerNumber));
            priority += 10;
        }

        // 12 Cards: Move 2 / priority 670-780
        for (int i = 0; i < 12; i += 1) {
            deck.add(new Card(Type.mov, (byte) 2, priority, playerNumber));
            priority += 10;
        }

        // 6 Cards: Move 3 / priority 790-840
        for (int i = 0; i < 6; i += 1) {
            deck.add(new Card(Type.mov, (byte) 3, priority, playerNumber));
            priority += 10;
        }
    }


    /**
     * Shuffles the cards, which were created before.
     */
    public void shuffle() {
        // Function from Java to shuffle the List
        Collections.shuffle(deck);

    }
    
    /**Function that turns a list of list of cards, each list representing
     * the Cards each player chose, into a list of where each list
     * represents one turn. 
     * @param maxCardCount maximum Cards one player has, used to determine
     *     the number of turns
     * @param inputList list of list of Cards(players)
     * @return List of List of Cards(turns)
     */
    public static LinkedList<LinkedList<Card>> transposeList(int maxCardCount,
            LinkedList<LinkedList<Card>> inputList) {
        
        LinkedList<LinkedList<Card>> outputList = new LinkedList<LinkedList<Card>>();
        for (int i = 0; i < maxCardCount; i++) {
            outputList.add(new LinkedList<Card>());
        }
        
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = 0; j < inputList.get(i).size(); j++) {
                Card card = inputList.get(i).get(j);
                System.out.println("cardowner:  " + card.getCardPlayerNumber());
                outputList.get(j).add(card);
            }
        }
        
        for (int i = 0; i < outputList.size(); i++) {
            outputList.set(i, Card.sortByPriority(outputList.get(i))); 
        }
        System.out.println("outputListsize:  " + outputList.size());
        return outputList;
        
        
        
        
    }
    
    
    
}
