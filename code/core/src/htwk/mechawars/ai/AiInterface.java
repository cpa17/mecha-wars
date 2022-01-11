package htwk.mechawars.ai;

import java.util.ArrayList;
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
    
    
    /**
     * Method that outputs randomly generated Cards for AI players.
     * @param playercount index of player that the Cards are generated for.
     * @return list of generated Cards. 
     */
    public LinkedList<Card> generateCards(ArrayList<Card> liste, int playercount, int availableCards);
}
