package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Robot;

/**
 * Class of a Gear.
 */
public class Gear extends Field {

    private int direction;
    private Texture tile;

    /**
     * Constructor of a Gear.
     */
    public Gear(int xcoor, int ycoor, int direction) {
        super(xcoor, ycoor);
        this.direction = direction;
        
        if (direction == 1) {
            this.tile = new Texture("mapAssets/gear/Gear01.png");
        } else {
            this.tile = new Texture("mapAssets/gear/Gear02.png");
        }
    }

    /**
     * Constructor of a Gear which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public Gear(int xcoor, int ycoor, int direction, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.direction = direction;

        if (!isTest) {
            if (direction == 1) {
                this.tile = new Texture("mapAssets/gear/Gear01.png");
            } else {
                this.tile = new Texture("mapAssets/gear/Gear02.png");
            }
        }
    }

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", direction: " + this.direction;
        return attributes;
    }

    public int getDirection() {
        return this.direction;
    }

    public Texture getTile() {
        return this.tile;
    }

    @Override
    public Robot cardAction(Robot robot) {
        if (this.direction == 1) {
            robot.turn((byte) 1);
        } else {
            robot.turn((byte) 3);
        }
        return robot;       
    }
    
    @Override
    public Robot turnAction(Robot robot) {
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

        Gear other = (Gear) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
            return false;
        }
        if (direction != other.direction) {
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
