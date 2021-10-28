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
    private Board board = new Board(2, 2);
    private LinkedList<Card> karten = new LinkedList<>();

    @Test
    public void damageTest() {

        assertEquals(0, robot.getDp());
        assertFalse(robot.getSd());
        assertEquals(3, robot.getLp());

        robot.damageUp();
        robot.damageUp();
        robot.damageUp();
        robot.setShutDown(true);

        assertEquals(3, robot.getDp());
        assertTrue(robot.getSd());
        assertFalse(robot.getDe());
        assertEquals(3, robot.getLp());

        board.move(karten, robot);
        robot.setShutDown(false);

        assertEquals(0, robot.getDp());
        assertFalse(robot.getSd());
        assertFalse(robot.getDe());
        assertEquals(3, robot.getLp());

        board.move(karten, robot);

        assertEquals(2, robot.getDp());
        assertFalse(robot.getSd());
        assertFalse(robot.getDe());
        assertEquals(3, robot.getLp());


        robot.setDe(true);
        assertTrue(robot.getDe());
        board.move(karten, robot);

        assertEquals(4, robot.getDp());
        assertFalse(robot.getSd());
        assertFalse(robot.getDe());
        assertEquals(3, robot.getLp());

        robot.setShutDown(true);
        robot.setDe(true);
        assertTrue(robot.getDe());
        board.move(karten, robot);

        assertEquals(0, robot.getDp());
        assertTrue(robot.getSd());
        assertFalse(robot.getDe());
        assertEquals(3, robot.getLp());


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
}
