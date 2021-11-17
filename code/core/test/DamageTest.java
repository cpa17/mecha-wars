import htwk.mechawars.board.Board;
import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import htwk.mechawars.cards.Card;
import htwk.mechawars.cards.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *  Test for the damage.
 */

public class DamageTest {
    private Robot robot = new Robot();
    private Board board = new Board(createBoardString(), true);
    private LinkedList<Card> karten = new LinkedList<>();

    @Test
    public void damageTest() {

        assertEquals(0, robot.getDamagePoints());
        assertFalse(robot.getShutDown());
        assertEquals(3, robot.getLifePoints());

        robot.damageUp();
        robot.damageUp();
        robot.damageUp();
        robot.setShutDown(true);

        assertEquals(3, robot.getDamagePoints());
        assertTrue(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());

        board.move(karten, robot);

        assertEquals(0, robot.getDamagePoints());
        assertFalse(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());

        board.move(karten, robot);

        assertEquals(2, robot.getDamagePoints());
        assertFalse(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());


        robot.setDestroyed(true);
        assertTrue(robot.getDestroyed());
        board.move(karten, robot);

        assertEquals(4, robot.getDamagePoints());
        assertFalse(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());

        robot.setNextRound(true);
        robot.setDestroyed(true);
        assertTrue(robot.getDestroyed());
        board.move(karten, robot);

        assertEquals(6, robot.getDamagePoints());
        assertTrue(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());

        board.move(karten, robot);

        assertEquals(0, robot.getDamagePoints());
        assertTrue(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());
    }

    /**
     * Methode that initialized the robot.
     */
    @BeforeEach
    public void initRobot() {
        robot.setDir(Dir.NORTH);
        robot.setXcoor(0);
        robot.setYcoor(0);
        robot.setShutDown(false);
        karten.add(new Card(Type.turn, (byte) 1, 0));
    }

    /**
     * Method that generates a String for the board constructor.
     */
    private String createBoardString() {
        String dummyVar = "";
        String boardString = dummyVar +
                "11000 11000 \n" +
                "11000 11000";
        return boardString;
    }
}
