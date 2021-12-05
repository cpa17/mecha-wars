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
    private Robot[] robotarray = new Robot[1];
    private Board board = new Board(createBoardString(), true);

    /**
     * Test for the Board Laser
     */
    @Test
    public void laserTest() {

        // Xcoor: 2, Ycoor: 3, Dir: NORTH, DamagePoints: 0
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(3, robotarray[0].getYcoor());
        assertEquals(Dir.NORTH, robotarray[0].getDir());
        assertEquals(0, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.mov, (byte) 3, 0));

        board.move(robotarray, true);

        // Xcoor: 2, Ycoor: 0, Dir: NORTH, DamagePoints: 1
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(0, robotarray[0].getYcoor());
        assertEquals(Dir.NORTH, robotarray[0].getDir());
        assertEquals(1, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.turn, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.turn, (byte) 3, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));

        board.move(robotarray, true);

        // Xcoor: 4, Ycoor: 2, Dir: EAST, DamagePoints: 2
        assertEquals(4, robotarray[0].getXcoor());
        assertEquals(2, robotarray[0].getYcoor());
        assertEquals(Dir.EAST, robotarray[0].getDir());
        assertEquals(2, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.turn, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 2, 0));

        board.move(robotarray, true);

        // Xcoor: 0, Ycoor: 2, Dir: WEST, DamagePoints: 3
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

        board.move(robotarray, true);

        // Xcoor: 2, Ycoor: 6, Dir: SOUTH, DamagePoints: 4
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(6, robotarray[0].getYcoor());
        assertEquals(Dir.SOUTH, robotarray[0].getDir());
        assertEquals(4, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.turn, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 3, 0));

        board.move(robotarray, true);

        // Xcoor: 2, Ycoor: 3, Dir: NORTH, DamagePoints: 4
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(3, robotarray[0].getYcoor());
        assertEquals(Dir.NORTH, robotarray[0].getDir());
        assertEquals(4, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
    }

    /**
     * Methode that initialized the robot.
     */
    @BeforeEach
    public void initRobot() {
        robotarray[0] = new Robot();
        robotarray[0].setDir(Dir.NORTH);
        robotarray[0].setXcoor(2);
        robotarray[0].setYcoor(3);
        robotarray[0].setShutDown(false);
    }

    /**
     * Method that generates a String for the board constructor.
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
}
