import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Test for the barriers.
 */

public class BarrierTest {
    private Robot[] robot = Robot.createRobots(1);
    private Board board = new Board(createBoardString(), true);

    @Test
    public void barrierTest() {

        // Test for scenarios when the robot direction is NORTH

        // xcoor: 5, ycoor: 5, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 2, robot);
        // xcoor: 5, ycoor: 4, Dir: NORTH
        assertEquals(5, robot[0].getXcoor());
        assertEquals(4, robot[0].getYcoor());

        robot[0].setYcoor(2);
        // xcoor: 5, ycoor: 2, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 2, robot);
        // xcoor: 5, ycoor: 0, Dir: NORTH
        assertEquals(5, robot[0].getXcoor());
        assertEquals(0, robot[0].getYcoor());

        robot[0].setXcoor(3);
        robot[0].setYcoor(5);
        // xcoor: 3, ycoor: 5, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 1, robot);
        // xcoor: 3, ycoor: 5, Dir: NORTH
        assertEquals(3, robot[0].getXcoor());
        assertEquals(5, robot[0].getYcoor());

        robot[0].setYcoor(4);
        // xcoor: 3, ycoor: 4, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 3, ycoor: 2, Dir: NORTH
        assertEquals(3, robot[0].getXcoor());
        assertEquals(2, robot[0].getYcoor());

        robot[0].setXcoor(1);
        robot[0].setYcoor(6);
        // xcoor: 1, ycoor: 6, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 1, robot);
        // xcoor: 1, ycoor: 6, Dir: NORTH
        assertEquals(1, robot[0].getXcoor());
        assertEquals(6, robot[0].getYcoor());

        robot[0].setYcoor(5);
        // xcoor: 1, ycoor: 5, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 1, ycoor: 3, Dir: NORTH
        assertEquals(1, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setYcoor(2);
        // xcoor: 1, ycoor: 2, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 2, robot);
        // xcoor: 1, ycoor: 1, Dir: NORTH
        assertEquals(1, robot[0].getXcoor());
        assertEquals(1, robot[0].getYcoor());


        // Test for scenarios when the robot direction is SOUTH
        robot[0].setDir(Dir.SOUTH);
        robot[0].setYcoor(0);
        // xcoor: 1, ycoor: 0, Dir: SOUTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 1, robot);
        // xcoor: 1, ycoor: 0, Dir: SOUTH
        assertEquals(1, robot[0].getXcoor());
        assertEquals(0, robot[0].getYcoor());

        robot[0].setYcoor(3);
        // xcoor: 1, ycoor: 3, Dir: SOUTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 1, ycoor: 5, Dir: SOUTH
        assertEquals(1, robot[0].getXcoor());
        assertEquals(5, robot[0].getYcoor());

        robot[0].setXcoor(3);
        robot[0].setYcoor(0);
        // xcoor: 3, ycoor: 0, Dir: SOUTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 2, robot);
        // xcoor: 3, ycoor: 1, Dir: SOUTH
        assertEquals(3, robot[0].getXcoor());
        assertEquals(1, robot[0].getYcoor());

        robot[0].setYcoor(2);
        // xcoor: 3, ycoor: 2, Dir: SOUTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 3, ycoor: 4, Dir: SOUTH
        assertEquals(3, robot[0].getXcoor());
        assertEquals(4, robot[0].getYcoor());

        robot[0].setXcoor(5);
        robot[0].setYcoor(0);
        // xcoor: 5, ycoor: 0, Dir: SOUTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 4, robot);
        // xcoor: 5, ycoor: 3, Dir: SOUTH
        assertEquals(5, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());


        // Test for scenarios when the robot direction is WEST
        robot[0].setDir(Dir.WEST);
        robot[0].setYcoor(5);
        // xcoor: 5, ycoor: 5, Dir: WEST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 4, robot);
        // xcoor: 2, ycoor: 5, Dir: WEST
        assertEquals(2, robot[0].getXcoor());
        assertEquals(5, robot[0].getYcoor());

        robot[0].setXcoor(6);
        robot[0].setYcoor(3);
        // xcoor: 6, ycoor: 3, Dir: WEST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 4, robot);
        // xcoor: 3, ycoor: 3, Dir: WEST
        assertEquals(3, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setXcoor(2);
        // xcoor: 2, ycoor: 3, Dir: WEST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 2, ycoor: 3, Dir: WEST
        assertEquals(2, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setXcoor(6);
        robot[0].setYcoor(1);
        // xcoor: 6, ycoor: 1, Dir: WEST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 2, robot);
        // xcoor: 6, ycoor: 1, Dir: WEST
        assertEquals(6, robot[0].getXcoor());
        assertEquals(1, robot[0].getYcoor());

        robot[0].setXcoor(5);
        // xcoor: 5, ycoor: 1, Dir: WEST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 3, ycoor: 1, Dir: WEST
        assertEquals(3, robot[0].getXcoor());
        assertEquals(1, robot[0].getYcoor());

        robot[0].setXcoor(2);
        // xcoor: 2, ycoor: 1, Dir: WEST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 1, ycoor: 1, Dir: WEST
        assertEquals(1, robot[0].getXcoor());
        assertEquals(1, robot[0].getYcoor());


        // Test for scenarios when the robot direction is EAST
        robot[0].setDir(Dir.EAST);
        robot[0].setXcoor(0);
        // xcoor: 0, ycoor: 1, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 1, robot);
        // xcoor: 0, ycoor: 1, Dir: EAST
        assertEquals(0, robot[0].getXcoor());
        assertEquals(1, robot[0].getYcoor());

        robot[0].setXcoor(1);
        // xcoor: 1, ycoor: 1, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 2, ycoor: 1, Dir: EAST
        assertEquals(2, robot[0].getXcoor());
        assertEquals(1, robot[0].getYcoor());

        robot[0].setXcoor(3);
        // xcoor: 3, ycoor: 1, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 5, ycoor: 1, Dir: EAST
        assertEquals(5, robot[0].getXcoor());
        assertEquals(1, robot[0].getYcoor());

        robot[0].setXcoor(0);
        robot[0].setYcoor(3);
        // xcoor: 0, ycoor: 3, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 2, robot);
        // xcoor: 1, ycoor: 3, Dir: EAST
        assertEquals(1, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setXcoor(2);
        // xcoor: 2, ycoor: 3, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 2, ycoor: 3, Dir: EAST
        assertEquals(2, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setXcoor(3);
        // xcoor: 3, ycoor: 3, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 6, ycoor: 3, Dir: EAST
        assertEquals(6, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setXcoor(0);
        robot[0].setYcoor(5);
        // xcoor: 0, ycoor: 5, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 1, ycoor: 5, Dir: EAST
        assertEquals(1, robot[0].getXcoor());
        assertEquals(5, robot[0].getYcoor());

        robot[0].setXcoor(2);
        robot[0].setYcoor(5);
        // xcoor: 2, ycoor: 5, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 3, robot);
        // xcoor: 5, ycoor: 5, Dir: EAST
        assertEquals(5, robot[0].getXcoor());
        assertEquals(5, robot[0].getYcoor());


        // Test for scenarios in which the robot passes walls but should not be stopped
        robot[0].setXcoor(0);
        robot[0].setYcoor(4);
        // xcoor: 0, ycoor: 4, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 6, robot);
        // xcoor: 6, ycoor: 4, Dir: EAST
        assertEquals(6, robot[0].getXcoor());
        assertEquals(4, robot[0].getYcoor());

        robot[0].setDir(Dir.WEST);
        robot[0].setXcoor(6);
        robot[0].setYcoor(2);
        // xcoor: 6, ycoor: 2, Dir: WEST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 6, robot);
        // xcoor: 0, ycoor: 2, Dir: WEST
        assertEquals(0, robot[0].getXcoor());
        assertEquals(2, robot[0].getYcoor());

        robot[0].setDir(Dir.SOUTH);
        robot[0].setXcoor(2);
        robot[0].setYcoor(0);
        // xcoor: 2, ycoor: 0, Dir: SOUTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 6, robot);
        // xcoor: 2, ycoor: 6, Dir: SOUTH
        assertEquals(2, robot[0].getXcoor());
        assertEquals(6, robot[0].getYcoor());

        robot[0].setDir(Dir.NORTH);
        robot[0].setXcoor(4);
        robot[0].setYcoor(6);
        // xcoor: 4, ycoor: 6, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) 6, robot);
        // xcoor: 4, ycoor: 0, Dir: NORTH
        assertEquals(4, robot[0].getXcoor());
        assertEquals(0, robot[0].getYcoor());


        // Test for scenarios in which the robot move backwards
        robot[0].setDir(Dir.EAST);
        robot[0].setXcoor(3);
        robot[0].setYcoor(3);
        // xcoor: 3, ycoor: 3, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) -1, robot);
        // xcoor: 3, ycoor: 3, Dir: EAST
        assertEquals(3, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setXcoor(2);
        robot[0].setYcoor(3);
        // xcoor: 2, ycoor: 3, Dir: EAST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) -1, robot);
        // xcoor: 3, ycoor: 3, Dir: EAST
        assertEquals(2, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setDir(Dir.WEST);
        robot[0].setXcoor(0);
        // xcoor: 0, ycoor: 3, Dir: WEST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) -1, robot);
        // xcoor: 1, ycoor: 3, Dir: WEST
        assertEquals(1, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        // xcoor: 1, ycoor: 3, Dir: WEST
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) -1, robot);
        // xcoor: 1, ycoor: 3, Dir: WEST
        assertEquals(1, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setDir(Dir.SOUTH);
        // xcoor: 1, ycoor: 3, Dir: SOUTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) -1, robot);
        // xcoor: 1, ycoor: 3, Dir: SOUTH
        assertEquals(1, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setXcoor(3);
        robot[0].setYcoor(4);
        // xcoor: 3, ycoor: 4, Dir: SOUTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) -1, robot);
        // xcoor: 3, ycoor: 3, Dir: SOUTH
        assertEquals(3, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());

        robot[0].setDir(Dir.NORTH);
        robot[0].setXcoor(1);
        robot[0].setYcoor(2);
        // xcoor: 1, ycoor: 2, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) -1, robot);
        // xcoor: 1, ycoor: 2, Dir: NORTH
        assertEquals(1, robot[0].getXcoor());
        assertEquals(2, robot[0].getYcoor());

        robot[0].setXcoor(3);
        // xcoor: 3, ycoor: 2, Dir: NORTH
        robot[0].moveInDirectionByCard(board.fieldmatrix, (byte) -1, robot);
        // xcoor: 3, ycoor: 3, Dir: NORTH
        assertEquals(3, robot[0].getXcoor());
        assertEquals(3, robot[0].getYcoor());
    }

    /**
     * Method that initialized the robot.
     */
    @BeforeEach
    public void initRobot() {
        robot[0].setDir(Dir.NORTH);
        robot[0].setXcoor(5);
        robot[0].setYcoor(5);
    }

    /**
     * Method that generates a String for the board constructor.
     * StandardField(1,1): barrier at the top and left
     * StandardField(1,3): barrier at the top and right
     * StandardField(1,5): barrier at the bottom and right
     * StandardField(3,1): barrier at the bottom and left
     * StandardField(3,3): barrier at the left
     * StandardField(3,5): barrier at the top
     * StandardField(5,1): barrier at the right
     * StandardField(5,3): barrier at the bottom
     */
    private String createBoardString() {
        String dummyVar = "";
        String boardString = dummyVar +
                "990000700 990000700 990000700 990000700 990000700 990000700 990000700 \n" +
                "990000700 991100700 990000700 991001700 990000700 990010700 990000700 \n" +
                "990000700 990000700 990000700 990000700 990000700 990000700 990000700 \n" +
                "990000700 990110700 990000700 991000700 990000700 990001700 990000700 \n" +
                "990000700 990000700 990000700 990000700 990000700 990000700 990000700 \n" +
                "990000700 990011700 990000700 990100700 990000700 990000700 990000700 \n" +
                "990000700 990000700 990000700 990000700 990000700 990000700 990000700";
        return boardString;
    }
}