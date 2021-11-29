package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;

/**
 * Class of a Side Barrier.
 */
public class BarrierSide extends Field {

    // attribute which locates the side of the barrier
    // 1 = left, 2 = top, 3 = right, 4 = bottom
    private int side;
    private Texture tile;

    /**
     * Constructor of a Side Barrier.
     */
    public BarrierSide(int xcoor, int ycoor, int side) {
        super(xcoor, ycoor);
        this.side = side;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "barrierside/"
                + "BarrierSide" + String.valueOf(side) + ".png"));
    }

    /**
     * Constructor of a Side Barrier which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public BarrierSide(int xcoor, int ycoor, int side, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.side = side;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "barrierside/"
                    + "BarrierSide" + String.valueOf(side) + ".png"));
        }
    }

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", side: " + this.side;
        return attributes;
    }

    public int getSide() {
        return this.side;
    }

    public Texture getTile() {
        return this.tile;
    }
    
    /**
     * Checks if the Roboter is trying to move over the barrier from the outside.
     * @param robot - the robot that moved
     * @param card - i dont have a clue
     */
    public void barrierCheck(Robot robot) {
        switch (this.side) {
            case 1 :    
                if (robot.getDir() == Dir.EAST || 
                        (robot.getDir() == Dir.WEST && robot.getLastMove() == -1)) {     
                    resetMove(robot.getLastMove(), robot);
                }
                break;
                    
            case 2 :    
                if (robot.getDir() == Dir.SOUTH || 
                        (robot.getDir() == Dir.NORTH && robot.getLastMove() == -1)) {
                    resetMove(robot.getLastMove(), robot);
                }
                break;
                    
            case 3 :    
                if (robot.getDir() == Dir.WEST || 
                        (robot.getDir() == Dir.EAST && robot.getLastMove() == -1)) { 
                    resetMove(robot.getLastMove(), robot);
                }
                break;
            
            case 4 :    
                if (robot.getDir() == Dir.NORTH || 
                        (robot.getDir() == Dir.SOUTH && robot.getLastMove() == -1)) {
                    resetMove(robot.getLastMove(), robot);
                }
                break;
                    
            default:    break;
        }
    }
    
    public void resetMove(int lastMove, Robot robot) {
        switch (lastMove) {
            case -1 :
                robot.moveInDirection((byte) 1);
            case 1 :
                robot.moveInDirection((byte) -1);
            case 2 :
                robot.moveInDirection((byte) -1);
                robot.moveInDirection((byte) -1);
            case 3 :
                robot.moveInDirection((byte) -1);
                robot.moveInDirection((byte) -1);
                robot.moveInDirection((byte) -1);
        }
    }
    
    @Override
    public Robot turnAction(Robot robot) {
        return robot;        
    }
    
    @Override
    public Robot cardAction(Robot robot) {
        switch (this.side) {
            case 1 :    
                if (robot.getDir() == Dir.NORTH || robot.getDir() == Dir.WEST) {
                    robot.setLocked(true);
                }
                break;
                    
            case 2 :    
                if (robot.getDir() == Dir.NORTH || robot.getDir() == Dir.EAST) {
                    robot.setLocked(true);
                }
                break;
                    
            case 3 :    
                if (robot.getDir() == Dir.SOUTH || robot.getDir() == Dir.EAST) {
                    robot.setLocked(true);
                }
                break;
            
            case 4 :    
                if (robot.getDir() == Dir.SOUTH || robot.getDir() == Dir.WEST) {
                    robot.setLocked(true);
                }
                break;
                    
            default:    break;
        }
        return robot;        
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        BarrierSide other = (BarrierSide) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
            return false;
        }
        if (side != other.side) {
            return false;
        }

        return true;
    }

    // Function that overwrites the hash code but has no further meaning or functionality
    // It only has to exist for the pipeline to work with the overridden equals function
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42;
    }
}
