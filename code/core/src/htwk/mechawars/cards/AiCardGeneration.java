package htwk.mechawars.cards;

import java.util.LinkedList;

/**
 * Class that generaters random Cards for AI testing. 
 */
public class AiCardGeneration {
    
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
            Card card = new Card(type, attributemovCount, cardAttributePriority);
            cards.add(card);
            System.out.println(card.toString() + "    Card number" + 1);    
        }
        System.out.println("*************************************************************");
        return cards;
    }

}
