import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Test for the class robot.
 */

public class BarrierTest {
    private Robot robot = new Robot();
    private Board board = new Board(createBoardString(), true);

    @Test
    public void barrierTest() {

        // Test for scenarios when the robot direction is NORTH

        // xcoor: 5, ycoor: 5, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 2);
        // xcoor: 5, ycoor: 4, Dir: NORTH
        assertEquals(5, robot.getXcoor());
        assertEquals(4, robot.getYcoor());

        robot.setYcoor(2);
        // xcoor: 5, ycoor: 2, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 2);
        // xcoor: 5, ycoor: 0, Dir: NORTH
        assertEquals(5, robot.getXcoor());
        assertEquals(0, robot.getYcoor());

        robot.setXcoor(3);
        robot.setYcoor(5);
        // xcoor: 3, ycoor: 5, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 1);
        // xcoor: 3, ycoor: 5, Dir: NORTH
        assertEquals(3, robot.getXcoor());
        assertEquals(5, robot.getYcoor());

        robot.setYcoor(4);
        // xcoor: 3, ycoor: 4, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 3, ycoor: 2, Dir: NORTH
        assertEquals(3, robot.getXcoor());
        assertEquals(2, robot.getYcoor());

        robot.setXcoor(1);
        robot.setYcoor(6);
        // xcoor: 1, ycoor: 6, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 1);
        // xcoor: 1, ycoor: 6, Dir: NORTH
        assertEquals(1, robot.getXcoor());
        assertEquals(6, robot.getYcoor());

        robot.setYcoor(5);
        // xcoor: 1, ycoor: 5, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 1, ycoor: 3, Dir: NORTH
        assertEquals(1, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setYcoor(2);
        // xcoor: 1, ycoor: 2, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 2);
        // xcoor: 1, ycoor: 1, Dir: NORTH
        assertEquals(1, robot.getXcoor());
        assertEquals(1, robot.getYcoor());


        // Test for scenarios when the robot direction is SOUTH
        robot.setDir(Dir.SOUTH);
        robot.setYcoor(0);
        // xcoor: 1, ycoor: 0, Dir: SOUTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 1);
        // xcoor: 1, ycoor: 0, Dir: SOUTH
        assertEquals(1, robot.getXcoor());
        assertEquals(0, robot.getYcoor());

        robot.setYcoor(3);
        // xcoor: 1, ycoor: 3, Dir: SOUTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 1, ycoor: 5, Dir: SOUTH
        assertEquals(1, robot.getXcoor());
        assertEquals(5, robot.getYcoor());

        robot.setXcoor(3);
        robot.setYcoor(0);
        // xcoor: 3, ycoor: 0, Dir: SOUTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 2);
        // xcoor: 3, ycoor: 1, Dir: SOUTH
        assertEquals(3, robot.getXcoor());
        assertEquals(1, robot.getYcoor());

        robot.setYcoor(2);
        // xcoor: 3, ycoor: 2, Dir: SOUTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 3, ycoor: 4, Dir: SOUTH
        assertEquals(3, robot.getXcoor());
        assertEquals(4, robot.getYcoor());

        robot.setXcoor(5);
        robot.setYcoor(0);
        // xcoor: 5, ycoor: 0, Dir: SOUTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 4);
        // xcoor: 5, ycoor: 3, Dir: SOUTH
        assertEquals(5, robot.getXcoor());
        assertEquals(3, robot.getYcoor());


        // Test for scenarios when the robot direction is WEST
        robot.setDir(Dir.WEST);
        robot.setYcoor(5);
        // xcoor: 5, ycoor: 5, Dir: WEST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 4);
        // xcoor: 2, ycoor: 5, Dir: WEST
        assertEquals(2, robot.getXcoor());
        assertEquals(5, robot.getYcoor());

        robot.setXcoor(6);
        robot.setYcoor(3);
        // xcoor: 6, ycoor: 3, Dir: WEST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 4);
        // xcoor: 3, ycoor: 3, Dir: WEST
        assertEquals(3, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setXcoor(2);
        // xcoor: 2, ycoor: 3, Dir: WEST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 2, ycoor: 3, Dir: WEST
        assertEquals(2, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setXcoor(6);
        robot.setYcoor(1);
        // xcoor: 6, ycoor: 1, Dir: WEST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 2);
        // xcoor: 6, ycoor: 1, Dir: WEST
        assertEquals(6, robot.getXcoor());
        assertEquals(1, robot.getYcoor());

        robot.setXcoor(5);
        // xcoor: 5, ycoor: 1, Dir: WEST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 3, ycoor: 1, Dir: WEST
        assertEquals(3, robot.getXcoor());
        assertEquals(1, robot.getYcoor());

        robot.setXcoor(2);
        // xcoor: 2, ycoor: 1, Dir: WEST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 1, ycoor: 1, Dir: WEST
        assertEquals(1, robot.getXcoor());
        assertEquals(1, robot.getYcoor());


        // Test for scenarios when the robot direction is EAST
        robot.setDir(Dir.EAST);
        robot.setXcoor(0);
        // xcoor: 0, ycoor: 1, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 1);
        // xcoor: 0, ycoor: 1, Dir: EAST
        assertEquals(0, robot.getXcoor());
        assertEquals(1, robot.getYcoor());

        robot.setXcoor(1);
        // xcoor: 1, ycoor: 1, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 2, ycoor: 1, Dir: EAST
        assertEquals(2, robot.getXcoor());
        assertEquals(1, robot.getYcoor());

        robot.setXcoor(3);
        // xcoor: 3, ycoor: 1, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 5, ycoor: 1, Dir: EAST
        assertEquals(5, robot.getXcoor());
        assertEquals(1, robot.getYcoor());

        robot.setXcoor(0);
        robot.setYcoor(3);
        // xcoor: 0, ycoor: 3, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 2);
        // xcoor: 1, ycoor: 3, Dir: EAST
        assertEquals(1, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setXcoor(2);
        // xcoor: 2, ycoor: 3, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 2, ycoor: 3, Dir: EAST
        assertEquals(2, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setXcoor(3);
        // xcoor: 3, ycoor: 3, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 6, ycoor: 3, Dir: EAST
        assertEquals(6, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setXcoor(0);
        robot.setYcoor(5);
        // xcoor: 0, ycoor: 5, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 1, ycoor: 5, Dir: EAST
        assertEquals(1, robot.getXcoor());
        assertEquals(5, robot.getYcoor());

        robot.setXcoor(2);
        robot.setYcoor(5);
        // xcoor: 2, ycoor: 5, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 3);
        // xcoor: 5, ycoor: 5, Dir: EAST
        assertEquals(5, robot.getXcoor());
        assertEquals(5, robot.getYcoor());


        // Test for scenarios in which the robot passes walls but should not be stopped
        robot.setXcoor(0);
        robot.setYcoor(4);
        // xcoor: 0, ycoor: 4, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 6);
        // xcoor: 6, ycoor: 4, Dir: EAST
        assertEquals(6, robot.getXcoor());
        assertEquals(4, robot.getYcoor());

        robot.setDir(Dir.WEST);
        robot.setXcoor(6);
        robot.setYcoor(2);
        // xcoor: 6, ycoor: 2, Dir: WEST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 6);
        // xcoor: 0, ycoor: 2, Dir: WEST
        assertEquals(0, robot.getXcoor());
        assertEquals(2, robot.getYcoor());

        robot.setDir(Dir.SOUTH);
        robot.setXcoor(2);
        robot.setYcoor(0);
        // xcoor: 2, ycoor: 0, Dir: SOUTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 6);
        // xcoor: 2, ycoor: 6, Dir: SOUTH
        assertEquals(2, robot.getXcoor());
        assertEquals(6, robot.getYcoor());

        robot.setDir(Dir.NORTH);
        robot.setXcoor(4);
        robot.setYcoor(6);
        // xcoor: 4, ycoor: 6, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) 6);
        // xcoor: 4, ycoor: 0, Dir: NORTH
        assertEquals(4, robot.getXcoor());
        assertEquals(0, robot.getYcoor());


        // Test for scenarios in which the robot move backwards
        robot.setDir(Dir.EAST);
        robot.setXcoor(3);
        robot.setYcoor(3);
        // xcoor: 3, ycoor: 3, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) -1);
        // xcoor: 3, ycoor: 3, Dir: EAST
        assertEquals(3, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setXcoor(2);
        robot.setYcoor(3);
        // xcoor: 2, ycoor: 3, Dir: EAST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) -1);
        // xcoor: 3, ycoor: 3, Dir: EAST
        assertEquals(2, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setDir(Dir.WEST);
        robot.setXcoor(0);
        // xcoor: 0, ycoor: 3, Dir: WEST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) -1);
        // xcoor: 1, ycoor: 3, Dir: WEST
        assertEquals(1, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        // xcoor: 1, ycoor: 3, Dir: WEST
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) -1);
        // xcoor: 1, ycoor: 3, Dir: WEST
        assertEquals(1, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setDir(Dir.SOUTH);
        // xcoor: 1, ycoor: 3, Dir: SOUTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) -1);
        // xcoor: 1, ycoor: 3, Dir: SOUTH
        assertEquals(1, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setXcoor(3);
        robot.setYcoor(4);
        // xcoor: 3, ycoor: 4, Dir: SOUTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) -1);
        // xcoor: 3, ycoor: 3, Dir: SOUTH
        assertEquals(3, robot.getXcoor());
        assertEquals(3, robot.getYcoor());

        robot.setDir(Dir.NORTH);
        robot.setXcoor(1);
        robot.setYcoor(2);
        // xcoor: 1, ycoor: 2, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) -1);
        // xcoor: 1, ycoor: 2, Dir: NORTH
        assertEquals(1, robot.getXcoor());
        assertEquals(2, robot.getYcoor());

        robot.setXcoor(3);
        // xcoor: 3, ycoor: 2, Dir: NORTH
        robot.moveInDirectionByCard(board.fieldmatrix, (byte) -1);
        // xcoor: 3, ycoor: 3, Dir: NORTH
        assertEquals(3, robot.getXcoor());
        assertEquals(3, robot.getYcoor());
    }

    /**
     * Methode that initialized the robot.
     */
    @BeforeEach
    public void initRobot() {
        robot.setDir(Dir.NORTH);
        robot.setXcoor(5);
        robot.setYcoor(5);
    }

    /**
     * Method that generates a String for the board constructor.
     * Corner barrier at Field(1,1) at the upper right corner
     * Corner barrier at Field(1,3) at the upper left corner
     * Corner barrier at Field(1,5) at the lower left corner
     * Corner barrier at Field(3,1) at the lower right corner
     * Side barrier at Field(3,3) at the left.
     * Side barrier at Field(3,5) at the top.
     * Side barrier at Field(5,1) at the right.
     * Side barrier at Field(5,3) at the bottom.
     */
    private String createBoardString() {
        String dummyVar = "";
        String boardString = dummyVar +
                "11000 11000 11000 11000 11000 11000 11000 \n" +
                "11000 10001 11000 10004 11000 10103 11000 \n" +
                "11000 11000 11000 11000 11000 11000 11000 \n" +
                "11000 10002 11000 10101 11000 10104 11000 \n" +
                "11000 11000 11000 11000 11000 11000 11000 \n" +
                "11000 10003 11000 10102 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 11000";
        return boardString;
    }
}