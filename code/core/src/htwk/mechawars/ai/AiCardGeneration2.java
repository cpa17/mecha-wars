package htwk.mechawars.ai;

import java.util.ArrayList;
import java.util.LinkedList;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Deck;
import htwk.mechawars.game.ScrollPanel;

/**
 * Class that generaters random Cards for AI testing. 
 */
public class AiCardGeneration2 implements AiInterface {

    
    
    /**
     * Method that outputs randomly generated Cards for AI players.
     * @param playercount index of player that the Cards are generated for.
     * @return list of generated Cards. 
     */
    public LinkedList<Card> generateCards(ArrayList<Card> liste, int playercount, int availableCards) {
        LinkedList<Card> cards = new LinkedList<Card>();
        System.out.println("");
        System.out.println("Player: " + (playercount + 1));
        System.out.println();
        System.out.println("Cards:  ");
        for (int i = 0; i < availableCards; i++) {
            int x =  0 + (int) (Math.random() * liste.size());
            Card card = liste.get(x);
            card.setCardPlayerNumber(playercount);
            cards.add(card);
            liste.remove(card);
            System.out.println(card.toString() + "    Card number" + 1); 
        }
        System.out.println("2222222222222222222222222222");
        return cards;
        
        
    }
}
