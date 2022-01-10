import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import htwk.mechawars.cards.Type;
import htwk.mechawars.cards.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Test for pushing robots by another robot.
 */

public class RobotPushesRobotTest {
    private Robot[] robot = Robot.createRobots(3);
    private Board board = new Board(createBoardString(), true);

    @Test
    public void robotPushesRobotTest() {

        // Test for scenario when a robot pushes two other robots

        // robot 0: xcoor: 0, ycoor: 5, Dir: EAST
        // robot 1: xcoor: 1, ycoor: 5, Dir: NORTH
        // robot 2: xcoor: 2, ycoor: 5, Dir: NORTH

        board.robotMovement(new Card(Type.mov, (byte) 3, 0), robot[0], robot);

        assertEquals(3, robot[0].getXcoor());
        assertEquals(5, robot[0].getYcoor());
        assertEquals(Dir.EAST, robot[0].getDir());

        assertEquals(4, robot[1].getXcoor());
        assertEquals(5, robot[1].getYcoor());
        assertEquals(Dir.NORTH, robot[1].getDir());

        assertEquals(5, robot[2].getXcoor());
        assertEquals(5, robot[2].getYcoor());
        assertEquals(Dir.NORTH, robot[2].getDir());


        // Test for scenario when a robot pushes another robot off the board

        robot[1].setDir(Dir.EAST);

        // robot 1: xcoor: 4, ycoor: 5, Dir: EAST
        // robot 2: xcoor: 5, ycoor: 5, Dir: NORTH

        board.robotMovement(new Card(Type.mov, (byte) 1, 0), robot[1], robot);

        assertEquals(5, robot[1].getXcoor());
        assertEquals(5, robot[1].getYcoor());
        assertEquals(Dir.EAST, robot[1].getDir());

        assertEquals(robot[2].getbackupCopyX(), robot[2].getXcoor());
        assertEquals(robot[2].getbackupCopyY(), robot[2].getYcoor());
        assertEquals(Dir.NORTH, robot[2].getDir());


        // Test for scenario when a robot pushes another robot towards a side barrier
        // so that both robots are stopped

        robot[1].setXcoor(1);
        robot[1].setYcoor(3);
        robot[2].setXcoor(1);
        robot[2].setYcoor(4);

        // robot 1: xcoor: 1, ycoor: 3, Dir: EAST
        // robot 2: xcoor: 1, ycoor: 4, Dir: NORTH

        board.robotMovement(new Card(Type.mov, (byte) 2, 0), robot[2], robot);

        assertEquals(1, robot[1].getXcoor());
        assertEquals(2, robot[1].getYcoor());
        assertEquals(Dir.EAST, robot[1].getDir());

        assertEquals(1, robot[2].getXcoor());
        assertEquals(3, robot[2].getYcoor());
        assertEquals(Dir.NORTH, robot[2].getDir());


        // Test for scenario when a robot pushes two other robot towards a corner barrier
        // so that all three robots are stopped

        robot[0].setYcoor(2);
        robot[2].setXcoor(2);
        robot[2].setYcoor(2);

        // robot 0: xcoor: 3, ycoor: 2, Dir: EAST
        // robot 1: xcoor: 1, ycoor: 2, Dir: EAST
        // robot 2: xcoor: 1, ycoor: 3, Dir: NORTH

        board.robotMovement(new Card(Type.mov, (byte) 2, 0), robot[1], robot);

        assertEquals(4, robot[0].getXcoor());
        assertEquals(2, robot[0].getYcoor());
        assertEquals(Dir.EAST, robot[0].getDir());

        assertEquals(2, robot[1].getXcoor());
        assertEquals(2, robot[1].getYcoor());
        assertEquals(Dir.EAST, robot[1].getDir());

        assertEquals(3, robot[2].getXcoor());
        assertEquals(2, robot[2].getYcoor());
        assertEquals(Dir.NORTH, robot[2].getDir());
    }

    /**
     * Method that initializes the robots.
     */
    @BeforeEach
    public void initRobot() {
        robot[0].setDir(Dir.EAST);
        robot[0].setXcoor(0);
        robot[0].setYcoor(5);
        robot[0].setStartX(0);
        robot[0].setStartY(5);

        robot[1] = new Robot();
        robot[1].setDir(Dir.NORTH);
        robot[1].setXcoor(1);
        robot[1].setYcoor(5);
        robot[1].setStartX(1);
        robot[1].setStartY(5);

        robot[2] = new Robot();
        robot[2].setDir(Dir.NORTH);
        robot[2].setXcoor(2);
        robot[2].setYcoor(5);
        robot[2].setStartX(2);
        robot[2].setStartY(5);
    }

    /**
     * Method that generates a String for the board constructor.
     * StandardField(1,2): barrier at the top
     * StandardField(5,2): barrier at the bottom and left
     */
    private String createBoardString() {
        String dummyVar = "";
        String boardString = dummyVar +
                "990000700 990000700 990000700 990000700 990000700 990000700 \n" +
                "990000700 990000700 990000700 990000700 990000700 990000700 \n" +
                "990000700 990100700 990000700 990000700 990000700 991100700 \n" +
                "990000700 990000700 990000700 990000700 990000700 990000700 \n" +
                "990000700 990000700 990000700 990000700 990000700 990000700 \n" +
                "990000700 990000700 990000700 990000700 990000700 990000700";
        return boardString;
    }
}
