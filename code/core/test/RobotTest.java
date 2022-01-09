import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import htwk.mechawars.fields.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Test for the class robot.
 */

public class RobotTest {
    private Robot[] robot = Robot.createRobots(1);
    private Board board = new Board(createBoardString(), true);

    @Test
    public void robotTest() {
        assertEquals(5, robot[0].getXcoor());
        assertEquals(5, robot[0].getYcoor());
        assertEquals(Dir.NORTH, robot[0].getDir());
        assertEquals(0, robot[0].getbackupCopyX());
        assertEquals(0, robot[0].getbackupCopyY());

        robot[0].turn((byte) 3);

        assertEquals(5, robot[0].getXcoor());
        assertEquals(5, robot[0].getYcoor());
        assertEquals(Dir.WEST, robot[0].getDir());
        assertEquals(0, robot[0].getbackupCopyX());
        assertEquals(0, robot[0].getbackupCopyY());

        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);

        assertEquals(2, robot[0].getXcoor());
        assertEquals(5, robot[0].getYcoor());
        assertEquals(Dir.WEST, robot[0].getDir());
        assertEquals(0, robot[0].getbackupCopyX());
        assertEquals(0, robot[0].getbackupCopyY());

        robot[0].turn((byte) 2);
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 1, robot);
        robot[0].turn((byte) 1);
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) -1, robot);
        robot[0].turn((byte) 2);
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);

        assertEquals(3, robot[0].getXcoor());
        assertEquals(1, robot[0].getYcoor());
        assertEquals(Dir.NORTH, robot[0].getDir());
        assertEquals(0, robot[0].getbackupCopyX());
        assertEquals(0, robot[0].getbackupCopyY());

    }

    /**
     * Method that initialized the robot.
     */
    @BeforeEach
    public void initRobot() {
        robot[0].setDir(Dir.NORTH);
        robot[0].setXcoor(5);
        robot[0].setYcoor(5);
        robot[0].getbackupCopyX();
        robot[0].getbackupCopyY();
    }

    /**
     * Method that generates a String for the board constructor.
     */
    private String createBoardString() {
        String dummyVar = "";
        String boardString = dummyVar +
                "11000 11000 11000 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000";
        return boardString;
    }
}
