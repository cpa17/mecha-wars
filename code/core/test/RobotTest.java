import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Gdx;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Test for the class robot.
 */

public class RobotTest {
    private Robot robot = new Robot();

    @Test
    public void robotTest() {
        assert robot.getbackupCopyX() >= 0;
        assert robot.getbackupCopyY() >= 0;
        
        assert robot.getbackupCopyX() <= Gdx.graphics.getWidth() / 2;
        assert robot.getbackupCopyY() <= Gdx.graphics.getHeight();

        assertEquals(5, robot.getXcoor());
        assertEquals(5, robot.getYcoor());
        assertEquals(Dir.NORTH, robot.getDir());
        assertEquals(0, robot.getStartX());
        assertEquals(0, robot.getStartY());

        robot.turn((byte) 3);

        assertEquals(5, robot.getXcoor());
        assertEquals(5, robot.getYcoor());
        assertEquals(Dir.WEST, robot.getDir());
        assertEquals(0, robot.getStartX());
        assertEquals(0, robot.getStartY());

        robot.moveInDirection((byte) 3);

        assertEquals(2, robot.getXcoor());
        assertEquals(5, robot.getYcoor());
        assertEquals(Dir.WEST, robot.getDir());
        assertEquals(0, robot.getStartX());
        assertEquals(0, robot.getStartY());

        robot.turn((byte) 2);
        robot.moveInDirection((byte) 1);
        robot.turn((byte) 1);
        robot.moveInDirection((byte) -1);
        robot.turn((byte) 2);
        robot.moveInDirection((byte) 3);

        assertEquals(3, robot.getXcoor());
        assertEquals(1, robot.getYcoor());
        assertEquals(Dir.NORTH, robot.getDir());
        assertEquals(0, robot.getStartX());
        assertEquals(0, robot.getStartY());

    }

    /**
     * Methode that initialized the robot.
     */
    @BeforeEach
    public void initRobot() {
        robot.setDir(Dir.NORTH);
        robot.setXcoor(5);
        robot.setYcoor(5);
        robot.setStartX(0);
        robot.setStartY(0);
    }

}
