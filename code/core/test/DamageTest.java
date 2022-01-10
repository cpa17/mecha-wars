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
    private Robot[] robot = Robot.createRobots(1);
    private Board board = new Board(createBoardString(), true);

    @Test
    public void damageTest() {
        /* Robot:
        *   DamagePoint: 0
        *   ShutDown: false
        *   NextRound: false
        *   LastRound: false
        *   Life: 3
        * */
        
        assertEquals(0, robot[0].getDamagePoints());
        assertFalse(robot[0].getShutDown());
        assertEquals(3, robot[0].getLifePoints());

        robot[0].damageUp();
        robot[0].damageUp();
        robot[0].damageUp();
        robot[0].setShutDown(true);
        robot[0].setNextRound(true);
        /* Robot:
         *   DamagePoint: 3
         *   ShutDown: true
         *   NextRound: true
         *   LastRound: false
         *   Life: 3
         * */

        assertEquals(3, robot[0].getDamagePoints());
        assertTrue(robot[0].getShutDown());
        assertFalse(robot[0].getDestroyed());
        assertEquals(3, robot[0].getLifePoints());

        board.move(robot, true);
        robot[0].setNextRound(false);
        /* Robot:
         *   DamagePoint: 0
         *   ShutDown: true
         *   NextRound: false
         *   LastRound: true
         *   Life: 3
         * */

        assertEquals(0, robot[0].getDamagePoints());
        assertTrue(robot[0].getShutDown());
        assertFalse(robot[0].getDestroyed());
        assertEquals(3, robot[0].getLifePoints());

        board.move(robot, true);
        /* Robot:
         *   DamagePoint: 2
         *   ShutDown: false
         *   NextRound: false
         *   LastRound: true
         *   Life: 3
         * */

        assertEquals(2, robot[0].getDamagePoints());
        assertFalse(robot[0].getShutDown());
        assertFalse(robot[0].getDestroyed());
        assertEquals(3, robot[0].getLifePoints());


        robot[0].setDestroyed(true);
        /* Robot:
         *   DamagePoint: 2
         *   ShutDown: false
         *   NextRound: false
         *   LastRound: false
         *   destroyed: true
         *   Life: 3
         * */

        assertTrue(robot[0].getDestroyed());
        board.move(robot, true);
        /* Robot:
         *   DamagePoint: 2
         *   ShutDown: false
         *   NextRound: false
         *   LastRound: false
         *   destroyed: false
         *   Life: 2
         * */

        assertEquals(2, robot[0].getDamagePoints());
        assertFalse(robot[0].getShutDown());
        assertFalse(robot[0].getDestroyed());
        assertEquals(2, robot[0].getLifePoints());

        robot[0].setNextRound(true);
        robot[0].setDestroyed(true);
        /* Robot:
         *   DamagePoint: 2
         *   ShutDown: false
         *   NextRound: true
         *   LastRound: false
         *   destroyed: true
         *   Life: 2
         * */

        assertTrue(robot[0].getDestroyed());

        board.move(robot, true);
        /* Robot:
         *   DamagePoint: 2
         *   ShutDown: true
         *   NextRound: true
         *   LastRound: false
         *   destroyed: false
         *   Life: 1
         * */

        assertEquals(0, robot[0].getDamagePoints());
        assertTrue(robot[0].getShutDown());
        assertFalse(robot[0].getDestroyed());
        assertEquals(1, robot[0].getLifePoints());

        board.move(robot, true);
        /* Robot:
         *   DamagePoint: 0
         *   ShutDown: true
         *   NextRound: true
         *   LastRound: true
         *   destroyed: false
         *   Life: 1
         * */

        assertEquals(0, robot[0].getDamagePoints());
        assertTrue(robot[0].getShutDown());
        assertFalse(robot[0].getDestroyed());
        assertEquals(1, robot[0].getLifePoints());
    }

    /**
     * Methode that initialized the robot.
     */
    @BeforeEach
    public void initRobot() {
        robot[0].setDir(Dir.NORTH);
        robot[0].setXcoor(0);
        robot[0].setYcoor(0);
        robot[0].setShutDown(false);
    }

    /**
     * Method that generates a String for the board constructor.
     */
    private String createBoardString() {
        return "11000 11000 \n" +
               "11000 11000";
    }
}
