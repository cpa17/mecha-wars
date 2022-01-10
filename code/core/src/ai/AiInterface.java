package ai;

import java.util.LinkedList;

import htwk.mechawars.board.Board;
import htwk.mechawars.board.Robot;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Deck;
import htwk.mechawars.game.GameScreen;
import htwk.mechawars.game.ScrollPanel;

public interface AiInterface {
    
    Robot[] players = Robot.getPlayers();
    
    Board board = GameScreen.getBoard();
    
    static Deck deck = ScrollPanel.getDeck();
    
    /**
     * Method that outputs randomly generated Cards for AI players.
     * @param playercount index of player that the Cards are generated for.
     * @return list of generated Cards. 
     */
    public static LinkedList<Card> generateCards(int playercount, int cardsChoosable) {
        LinkedList<Card> cards = new LinkedList<Card>();
        System.out.println("");
        System.out.println("Player: " + (playercount + 1));
        System.out.println();
        System.out.println("Cards:  ");
        for (int i = 0; i < 5; i++) {
            int x =  0 + (int) (Math.random() * deck.getDeck().size());
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
