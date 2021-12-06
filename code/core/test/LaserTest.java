import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class that tests the Lasers.
 */
public class LaserTest {
    private Robot[] robotarray = new Robot[2];
    private Board boardLaserBoard = new Board(createBoardString(), true);
    private Board robotLaserBoard = new Board(createBoardString2(), true);

    /**
     * Test for the Board Laser.
     */
    @Test
    public void boardLaserTest() {

        // xcoor: 2, ycoor: 3, Dir: NORTH, DamagePoints: 0
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(3, robotarray[0].getYcoor());
        assertEquals(Dir.NORTH, robotarray[0].getDir());
        assertEquals(0, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.mov, (byte) 3, 0));

        // Robot moves in front of the laser 1
        boardLaserBoard.move(robotarray, true);

        // xcoor: 2, ycoor: 0, Dir: NORTH, DamagePoints: 1
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(0, robotarray[0].getYcoor());
        assertEquals(Dir.NORTH, robotarray[0].getDir());
        assertEquals(1, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.turn, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.turn, (byte) 3, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));

        // Robot moves in front of the laser 2
        boardLaserBoard.move(robotarray, true);

        // xcoor: 4, ycoor: 2, Dir: EAST, DamagePoints: 2
        assertEquals(4, robotarray[0].getXcoor());
        assertEquals(2, robotarray[0].getYcoor());
        assertEquals(Dir.EAST, robotarray[0].getDir());
        assertEquals(2, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.turn, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));

        // Robot moves in front of the laser 3
        boardLaserBoard.move(robotarray, true);

        // xcoor: 0, ycoor: 2, Dir: WEST, DamagePoints: 3
        assertEquals(0, robotarray[0].getXcoor());
        assertEquals(2, robotarray[0].getYcoor());
        assertEquals(Dir.WEST, robotarray[0].getDir());
        assertEquals(3, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.turn, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.turn, (byte) 1, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));

        // Robot moves in front of the laser 4 (which is blocked by the other robot)
        boardLaserBoard.move(robotarray, true);

        // xcoor: 2, ycoor: 6, Dir: SOUTH, DamagePoints: 3 (because the other robot is in the way)
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(6, robotarray[0].getYcoor());
        assertEquals(Dir.SOUTH, robotarray[0].getDir());
        assertEquals(3, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.turn, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 3, 0));

        // Robot moves back to its starting position
        boardLaserBoard.move(robotarray, true);

        // xcoor: 2, ycoor: 3, Dir: NORTH, DamagePoints: 3
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(3, robotarray[0].getYcoor());
        assertEquals(Dir.NORTH, robotarray[0].getDir());
        assertEquals(3, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
    }

    /**
     * Test for the Robot Laser.
     */
    @Test
    public void robotLaserTest() {

        robotarray[0].setYcoor(6);
        robotarray[0].setDir(Dir.EAST);

        robotLaserBoard.checkRobotLaser(robotarray);

        /** robot a: xcoor = 2, ycoor = 6, Dir = EAST, DamagePoints = 0
         *  robot b: xcoor = 3, ycoor = 6, Dir = NORTH, DamagePoints = 1
         */
        assertEquals(0, robotarray[0].getDamagePoints());
        assertEquals(1, robotarray[1].getDamagePoints());

        robotarray[0].setXcoor(3);
        robotarray[0].setYcoor(4);
        robotarray[1].setXcoor(5);
        robotarray[1].setYcoor(4);
        robotarray[1].setDir(Dir.WEST);

        robotLaserBoard.checkRobotLaser(robotarray);

        /** robot a: xcoor = 3, ycoor = 4, Dir = EAST, DamagePoints = 1
         *  robot b: xcoor = 5, ycoor = 4, Dir = WEST, DamagePoints = 2
         */
        assertEquals(1, robotarray[0].getDamagePoints());
        assertEquals(2, robotarray[1].getDamagePoints());

        robotarray[0].setXcoor(4);
        robotarray[1].setXcoor(4);
        robotarray[1].setYcoor(2);
        robotarray[1].setDir(Dir.SOUTH);

        robotLaserBoard.checkRobotLaser(robotarray);

        /** robot a: xcoor = 4, ycoor = 4, Dir = EAST, DamagePoints = 2
         *  robot b: xcoor = 4, ycoor = 2, Dir = SOUTH, DamagePoints = 2
         */
        assertEquals(2, robotarray[0].getDamagePoints());
        assertEquals(2, robotarray[1].getDamagePoints());

        robotarray[0].setYcoor(5);

        robotLaserBoard.checkRobotLaser(robotarray);

        /** robot a: xcoor = 4, ycoor = 5, Dir = EAST, DamagePoints = 2
         *  robot b: xcoor = 4, ycoor = 2, Dir = SOUTH, DamagePoints = 2
         */
        assertEquals(2, robotarray[0].getDamagePoints());
        assertEquals(2, robotarray[1].getDamagePoints());

        robotarray[0].setXcoor(2);
        robotarray[0].setDir(Dir.NORTH);
        robotarray[1].setXcoor(2);

        robotLaserBoard.checkRobotLaser(robotarray);

        /** robot a: xcoor = 2, ycoor = 5, Dir = NORTH, DamagePoints = 2
         *  robot b: xcoor = 2, ycoor = 2, Dir = SOUTH, DamagePoints = 2
         */
        assertEquals(2, robotarray[0].getDamagePoints());
        assertEquals(2, robotarray[1].getDamagePoints());

        robotarray[0].setYcoor(0);
        robotarray[0].setDir(Dir.SOUTH);
        robotarray[1].setDir(Dir.NORTH);

        robotLaserBoard.checkRobotLaser(robotarray);

        /** robot a: xcoor = 2, ycoor = 0, Dir = SOUTH, DamagePoints = 3
         *  robot b: xcoor = 2, ycoor = 2, Dir = NORTH, DamagePoints = 3
         */
        assertEquals(3, robotarray[0].getDamagePoints());
        assertEquals(3, robotarray[1].getDamagePoints());
    }

    /**
     * Methode that initialized the robots.
     */
    @BeforeEach
    public void initRobot() {
        robotarray[0] = new Robot();
        robotarray[0].setDir(Dir.NORTH);
        robotarray[0].setXcoor(2);
        robotarray[0].setYcoor(3);

        robotarray[1] = new Robot();
        robotarray[1].setDir(Dir.NORTH);
        robotarray[1].setXcoor(3);
        robotarray[1].setYcoor(6);
    }

    /**
     * Method that generates a String for the board constructor.
     * Laser 1 from Field(1,0) to Field(3,0)
     * Laser 2 from Field(4,2) to Field(4,4)
     * Laser 3 from Field(0,4) to Field(0,2)
     * Laser 4 from Field(3,6) to Field(1,6)
     */
    private String createBoardString() {
        String dummyVar = "";
        String boardString = dummyVar +
                "11000 10800 10804 10808 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 11000 \n" +
                "10807 11000 11000 11000 10801 11000 11000 \n" +
                "10805 11000 11000 11000 10805 11000 11000 \n" +
                "10803 11000 11000 11000 10809 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 11000 \n" +
                "11000 10806 10804 10802 11000 11000 11000";
        return boardString;
    }

    /**
     * Method that generates a String for the board constructor.
     * Side barrier at Field(4,4) at the bottom.
     * Corner barrier at Field(2,2) at the lower left corner
     */
    private String createBoardString2() {
        String dummyVar = "";
        String boardString = dummyVar +
                "11000 11000 11000 11000 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 11000 \n" +
                "11000 11000 10004 11000 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 11000 \n" +
                "11000 11000 11000 11000 10104 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 11000 \n" +
                "11000 11000 11000 11000 11000 11000 11000";
        return boardString;
    }
}
