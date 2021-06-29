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
    private Board board = new Board(4, 4);
    private Robot robot = new Robot();

    public int[][] startMatrix = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 1}
    };

    public int[][] midMatrix = {
            {0, 0, 3, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };

    public int[][] endMatrix = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 2}
    };



    @Test
    public void boardTest() {
        LinkedList<Card> phase1 = new LinkedList<>();
        phase1.add(new Card(Type.mov, (byte) 3));
        phase1.add(new Card(Type.turn, (byte) 3));
        phase1.add(new Card(Type.mov, (byte) 1));
        phase1.add(new Card(Type.turn, (byte) 1));
        phase1.add(new Card(Type.turn, (byte) 2));

        LinkedList<Card> phase2 = new LinkedList<>();
        phase2.add(new Card(Type.mov, (byte) 2));
        phase2.add(new Card(Type.turn, (byte) 2));
        phase2.add(new Card(Type.mov, (byte) -1));
        phase2.add(new Card(Type.turn, (byte) 1));
        phase2.add(new Card(Type.mov, (byte) 2));

        assertArrayEquals(startMatrix, board.matrix);
        board.move(phase1, robot);
        assertArrayEquals(midMatrix, board.matrix);
        board.move(phase2, robot);
        assertArrayEquals(endMatrix, board.matrix);

    }

    /**
     * Function that generates the board and places the robot.
     */
    @BeforeEach
    public void initBoard() {
        board.startRobot(3, 3, Dir.NORTH, robot);
    }

}

