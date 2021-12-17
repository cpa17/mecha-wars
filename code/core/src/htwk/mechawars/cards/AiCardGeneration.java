package htwk.mechawars.cards;

import java.util.LinkedList;

import htwk.mechawars.game.ScrollPanel;

/**
 * Class that generaters random Cards for AI testing. 
 */
public class AiCardGeneration {
    private static Deck deck = ScrollPanel.getDeck();
    

    /**
     * Method that outputs randomly generated Cards for AI players.
     * @param playercount index of player that the Cards are generated for.
     * @return list of generated Cards. 
     */
    public static LinkedList<Card> generateRandomAiCards(int playercount) {
        int[] moveCounts = {-1, 1, 2, 3};
        LinkedList<Card> cards = new LinkedList<Card>();
        System.out.println("");
        System.out.println("Player: " + (playercount + 1));
        System.out.println();
        System.out.println("Cards:  ");
        for (int i = 0; i < 5; i++) {
            Type type = Type.mov;
            int dir = (int) Math.round(Math.random());
            switch (dir) {
                case 0: type = Type.mov;
                    break;
                case 1: type = Type.turn;
                    break;
                default: type = Type.mov;
                    break;
            }
            int moveSetter = (int) Math.round(Math.random() * (4) - 0.5);
            byte attributemovCount = (byte) moveCounts[moveSetter];
            int cardAttributePriority = (int) Math.round(Math.random() * 3);
            Card card = new Card(type, attributemovCount, cardAttributePriority, playercount);
            cards.add(card);
            System.out.println(card.toString() + "    Card number" + 1);    
        }
        System.out.println("*************************************************************");
        return cards;
    }

    public static LinkedList<Card> generateRandomAiCardsfromDeck(int playercount) {
        LinkedList<Card> cards = new LinkedList<Card>();
        System.out.println("");
        System.out.println("Player: " + (playercount + 1));
        System.out.println();
        System.out.println("Cards:  ");
        for (int i = 0; i < 5; i++) {
            int x =  0 + (int)(Math.random() * deck.getDeck().size());
            Card card = deck.getDeck().get(x);
            card.setCardPlayerNumber(playercount);
            cards.add(card);
            deck.getDeck().remove(card);
            System.out.println(card.toString() + "    Card number" + 1); 
            }
        System.out.println("*************************************************************");
        return cards;
        
        
    }
}
