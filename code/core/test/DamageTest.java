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
        /* Robot:
        *   DamagePoint: 0
        *   ShutDown: false
        *   NextRound: false
        *   LastRound: false
        *   Life: 3
        * */
        
        assertEquals(0, robot.getDamagePoints());
        assertFalse(robot.getShutDown());
        assertEquals(3, robot.getLifePoints());

        robot.damageUp();
        robot.damageUp();
        robot.damageUp();
        robot.setShutDown(true);
        robot.setNextRound(true);
        /* Robot:
         *   DamagePoint: 3
         *   ShutDown: true
         *   NextRound: true
         *   LastRound: false
         *   Life: 3
         * */

        assertEquals(3, robot.getDamagePoints());
        assertTrue(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());

        board.moveSingleRobot(karten, robot, true);
        robot.setNextRound(false);
        /* Robot:
         *   DamagePoint: 0
         *   ShutDown: true
         *   NextRound: false
         *   LastRound: true
         *   Life: 3
         * */

        assertEquals(0, robot.getDamagePoints());
        assertTrue(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());

        board.moveSingleRobot(karten, robot, true);
        /* Robot:
         *   DamagePoint: 2
         *   ShutDown: false
         *   NextRound: false
         *   LastRound: true
         *   Life: 3
         * */

        assertEquals(2, robot.getDamagePoints());
        assertFalse(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());


        robot.setDestroyed(true);
        /* Robot:
         *   DamagePoint: 2
         *   ShutDown: false
         *   NextRound: false
         *   LastRound: false
         *   destroyed: true
         *   Life: 3
         * */

        assertTrue(robot.getDestroyed());
        board.moveSingleRobot(karten, robot, true);
        /* Robot:
         *   DamagePoint: 4
         *   ShutDown: false
         *   NextRound: false
         *   LastRound: false
         *   destroyed: false
         *   Life: 3
         * */

        assertEquals(4, robot.getDamagePoints());
        assertFalse(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());

        robot.setNextRound(true);
        robot.setDestroyed(true);
        /* Robot:
         *   DamagePoint: 4
         *   ShutDown: false
         *   NextRound: true
         *   LastRound: false
         *   destroyed: true
         *   Life: 3
         * */

        assertTrue(robot.getDestroyed());

        board.moveSingleRobot(karten, robot, true);
        /* Robot:
         *   DamagePoint: 4
         *   ShutDown: true
         *   NextRound: true
         *   LastRound: false
         *   destroyed: false
         *   Life: 3
         * */

        assertEquals(0, robot.getDamagePoints());
        assertTrue(robot.getShutDown());
        assertFalse(robot.getDestroyed());
        assertEquals(3, robot.getLifePoints());

        board.moveSingleRobot(karten, robot, true);
        /* Robot:
         *   DamagePoint: 0
         *   ShutDown: true
         *   NextRound: true
         *   LastRound: true
         *   destroyed: false
         *   Life: 3
         * */

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
        return "11000 11000 \n" +
               "11000 11000";
    }
}
