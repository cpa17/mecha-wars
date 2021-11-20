import static org.junit.jupiter.api.Assertions.assertEquals;

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
    private Board board = new Board(createBoardString(), true);
    private Board startBoard = new Board(createStartString(), true);
    private Board midBoard = new Board(createMidString(), true);
    private Board endBoard = new Board(createEndString(), true);

    private Robot robot = new Robot();

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

        assertBoardEquals(board, startBoard);
        board.move(phase1, robot);
        assertBoardEquals(board, midBoard);
        board.move(phase2, robot);
        assertBoardEquals(board, endBoard);
    }

    /**
     * Method that generates the board and places the robot.
     */
    @BeforeEach
    public void initBoard() {
        board.startRobot(3, 3, Dir.NORTH, robot);
    }

    /**
     * Method that generates a String for the board constructor.
     */
    private String createBoardString() {
        String dummyVar = "";
        String boardString = dummyVar +
                "11000 10001 10101 10200 \n" +
                "10301 10401 10521 10621 \n" +
                "10701 10800 10901 11101 \n" +
                "11000 11000 11000 11000";
        return boardString;
    }

    private String createStartString() {
        String dummyVar = "";
        String startString = dummyVar +
                "11000 10001 10101 10200 \n" +
                "10301 10401 10521 10621 \n" +
                "10701 10800 10901 11101 \n" +
                "11000 11000 11000 11000";
        return startString;
    }

    private String createMidString() {
        String dummyVar = "";
        String midString = dummyVar +
                "11000 10001 10101 10200 \n" +
                "10301 10401 10521 10621 \n" +
                "10701 10800 10901 11101 \n" +
                "11000 11000 11000 11000";
        return midString;
    }

    private String createEndString() {
        String dummyVar = "";
        String endString = dummyVar +
                "11000 10001 10101 10200 \n" +
                "10301 10401 10521 10621 \n" +
                "10701 10800 10901 11101 \n" +
                "11000 11000 11000 11000";
        return endString;
    }

    private void assertBoardEquals(Board current, Board expected) {
        assertEquals(expected.fieldmatrix.length, current.fieldmatrix.length);
        assertEquals(expected.fieldmatrix[1].length, current.fieldmatrix[1].length);
        for (int i = 0; i < expected.fieldmatrix.length; i++) {
            for (int j = 0; j < expected.fieldmatrix[i].length; j++) {
                assertEquals(expected.fieldmatrix[i][j], current.fieldmatrix[i][j]);
            }
        }
    }
}