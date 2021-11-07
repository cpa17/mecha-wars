import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import java.util.LinkedList;

import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class that tests the creation of the game board and places the robot.
 */

public class BoardTest {
    private Board board = new Board(4, 4, true);
    private Board boardtxt = new Board();

    private Robot robot = new Robot();
    private Robot robottxt = new Robot();

    public int[][] startMatrix = {
            {11000, 11000, 11000, 11000},
            {11000, 11000, 11000, 11000},
            {11000, 11000, 11000, 11000},
            {11000, 11000, 11000, 11000}
    };

    public int[][] midMatrix = {
            {11000, 11000, 11000, 11000},
            {11000, 11000, 11000, 11000},
            {11000, 11000, 11000, 11000},
            {11000, 11000, 11000, 11000}
    };

    public int[][] endMatrix = {
            {11000, 11000, 11000, 11000},
            {11000, 11000, 11000, 11000},
            {11000, 11000, 11000, 11000},
            {11000, 11000, 11000, 11000}
    };
    
    String dummyVar = "";   
    public String boardString = dummyVar + 
            "11000 11000 11000 11000 \n" + 
            "11000 11000 11000 11000 \n" +
            "11000 11000 11000 11000 \n" +
            "11000 11000 11000 11000";

    @Test
    public void boardTest() {
        LinkedList<Card> phase1 = new LinkedList<>();
        phase1.add(new Card(Type.mov, (byte) 3, 0));
        phase1.add(new Card(Type.turn, (byte) 3, 0));
        phase1.add(new Card(Type.mov, (byte) 1, 0));
        phase1.add(new Card(Type.turn, (byte) 1, 0));
        phase1.add(new Card(Type.turn, (byte) 2, 0));

        LinkedList<Card> phase2 = new LinkedList<>();
        phase2.add(new Card(Type.mov, (byte) 2, 0));
        phase2.add(new Card(Type.turn, (byte) 2, 0));
        phase2.add(new Card(Type.mov, (byte) -1, 0));
        phase2.add(new Card(Type.turn, (byte) 1, 0));
        phase2.add(new Card(Type.mov, (byte) 2, 0));

        assertArrayEquals(startMatrix, board.matrix);
        board.move(phase1, robot);
        assertArrayEquals(midMatrix, board.matrix);
        board.move(phase2, robot);
        assertArrayEquals(endMatrix, board.matrix);

        assertArrayEquals(startMatrix, boardtxt.matrix);
        boardtxt.move(phase1, robottxt);
        assertArrayEquals(midMatrix, boardtxt.matrix);
        boardtxt.move(phase2, robottxt);
        assertArrayEquals(endMatrix, boardtxt.matrix);
    }

    /**
     * Methode that generates the board and places the robot.
     */
    @BeforeEach
    public void initBoard() {
        board.startRobot(3, 3, Dir.NORTH, robot);

        boardtxt = new Board(boardString, true);
        boardtxt.startRobot(3, 3, Dir.NORTH, robottxt);
    }

}