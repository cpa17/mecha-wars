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

        // Robot moves in front of the laser 1
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

        // Robot moves in front of the laser 2
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

        // Robot moves in front of the laser 3
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

        // Robot moves in front of the laser 4 (which is blocked by the other robot)
        board.move(robotarray, true);

        // Xcoor: 2, Ycoor: 6, Dir: SOUTH, DamagePoints: 3 (because the other robot is in the way)
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(6, robotarray[0].getYcoor());
        assertEquals(Dir.SOUTH, robotarray[0].getDir());
        assertEquals(3, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
        robotarray[0].addCard(new Card(Type.turn, (byte) 2, 0));
        robotarray[0].addCard(new Card(Type.mov, (byte) 3, 0));

        // Robot moves back to its starting position
        board.move(robotarray, true);

        // Xcoor: 2, Ycoor: 3, Dir: NORTH, DamagePoints: 3
        assertEquals(2, robotarray[0].getXcoor());
        assertEquals(3, robotarray[0].getYcoor());
        assertEquals(Dir.NORTH, robotarray[0].getDir());
        assertEquals(3, robotarray[0].getDamagePoints());

        robotarray[0].resetList();
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

        // robot that only exists to block lasers 4
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
}
