package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Robot;

/**
 * Class of a Field.
 */
public class Field {

    protected int xcoor;
    protected int ycoor;
    protected Texture tile;

    // attributes that indicates the number of vertical/ horizontal lasers
    // and whether it is the start of the laser or not
    // 1 = 1 laser, 2 = 2 laser, 3 = 3 laser, 4 = start of 1 laser,
    // 5 = start of 2 lasers, 6 = start of 3 lasers, 9 = 0 laser
    protected int laserVertical;
    protected int laserHorizontal;

    // attributes that indicate whether there is a barrier on the left/ top/ right/ bottom
    protected boolean barrierLeft;
    protected boolean barrierTop;
    protected boolean barrierRight;
    protected boolean barrierBottom;

    /**
     * Constructor of a Field with no lasers or barriers.
     */
    public Field(int xcoor, int ycoor) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.tile = new Texture("mapAssets/StandardField.png");

        this.laserVertical = 9;
        this.laserHorizontal = 9;

        this.barrierLeft = false;
        this.barrierTop = false;
        this.barrierRight = false;
        this.barrierBottom = false;
    }

    /**
     * Constructor of a Field with no lasers or barriers which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public Field(int xcoor, int ycoor, boolean isTest) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        if (!isTest) {
            this.tile = new Texture("mapAssets/StandardField.png");
        }

        this.laserVertical = 9;
        this.laserHorizontal = 9;

        this.barrierLeft = false;
        this.barrierTop = false;
        this.barrierRight = false;
        this.barrierBottom = false;
    }

    /**
     * Constructor of a Field with barrier- and laser-attributes.
     */
    public Field(int xcoor, int ycoor, int laserVertical, int laserHorizontal, boolean barrierLeft,
                 boolean barrierTop, boolean barrierRight, boolean barrierBottom) {

        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.tile = new Texture("mapAssets/StandardField.png");

        this.laserVertical = laserVertical;
        this.laserHorizontal = laserHorizontal;

        this.barrierLeft = barrierLeft;
        this.barrierTop = barrierTop;
        this.barrierRight = barrierRight;
        this.barrierBottom = barrierBottom;
    }

    /**
     * Constructor of a Field with barrier- and laser-attributes which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public Field(int xcoor, int ycoor, int laserVertical, int laserHorizontal, boolean barrierLeft,
                 boolean barrierTop, boolean barrierRight, boolean barrierBottom, boolean isTest) {

        this.xcoor = xcoor;
        this.ycoor = ycoor;
        if (!isTest) {
            this.tile = new Texture("mapAssets/StandardField.png");
        }

        this.laserVertical = laserVertical;
        this.laserHorizontal = laserHorizontal;

        this.barrierLeft = barrierLeft;
        this.barrierTop = barrierTop;
        this.barrierRight = barrierRight;
        this.barrierBottom = barrierBottom;
    }

    /**
     * Method that create a String from a Field that contains the attribute values.
     *
     * @return A String with the attribute values of the Field
     */
    public String toString() {
        String attributes = "";
        attributes = attributes + "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor;

        if (this.laserVertical != 9) {
            attributes = attributes + ", laserVertical: " + this.laserVertical;
        }
        if (this.laserHorizontal != 9) {
            attributes = attributes + ", laserHorizontal: " + this.laserHorizontal;
        }
        if (this.barrierLeft) {
            attributes = attributes + ", barrierLeft: " + this.barrierLeft;
        }
        if (this.barrierTop) {
            attributes = attributes + ", barrierTop: " + this.barrierTop;
        }
        if (this.barrierRight) {
            attributes = attributes + ", barrierRight: " + this.barrierRight;
        }
        if (this.barrierBottom) {
            attributes = attributes + ", barrierBottom: " + this.barrierBottom;
        }

        return attributes;
    }
    
    /**
     * On the end of the turn a action can be done.
     * 
     * @param robot -> give the function the robot, that have an action.
     * @return the instance robot
     */
    public Robot cardAction(Robot robot) {
        return robot; 
    }
    
    /**
     * On the end of card action a action can be done.
     * 
     * @param robot -> give the function the robot, that have an action.
     * @return the instance robot
     */
    public Robot turnAction(Robot robot) {
        return robot; 
    }

    // Getters. ------------------------------------------------------------------------------

    public int getXcoor() {
        return this.xcoor;
    }

    public int getYcoor() {
        return this.ycoor;
    }

    /** Dummy.
     *
     * @return .
     */
    public Texture getTile() {
        return this.tile;
    }

    public int getLaserVertical() {
        return laserVertical;
    }

    public int getLaserHorizontal() {
        return laserHorizontal;
    }

    public boolean getBarrierLeft() {
        return barrierLeft;
    }

    public boolean getBarrierTop() {
        return barrierTop;
    }

    public boolean getBarrierRight() {
        return barrierRight;
    }

    public boolean getBarrierBottom() {
        return barrierBottom;
    }

    // Setters. ------------------------------------------------------------------------------

    public void setLaserVertical(int laserVertical) {
        this.laserVertical = laserVertical;
    }

    public void setLaserHorizontal(int laserHorizontal) {
        this.laserHorizontal = laserHorizontal;
    }

    public void setBarrierLeft(boolean barrierLeft) {
        this.barrierLeft = barrierLeft;
    }

    public void setBarrierTop(boolean barrierTop) {
        this.barrierTop = barrierTop;
    }

    public void setBarrierRight(boolean barrierRight) {
        this.barrierRight = barrierRight;
    }

    public void setBarrierBottom(boolean barrierBottom) {
        this.barrierBottom = barrierBottom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        StandardField other = (StandardField) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (laserVertical != other.laserVertical) {
            return false;
        }
        if (laserHorizontal != other.laserHorizontal) {
            return false;
        }
        if (barrierLeft != other.barrierLeft) {
            return false;
        }
        if (barrierTop != other.barrierTop) {
            return false;
        }
        if (barrierRight != other.barrierRight) {
            return false;
        }
        if (barrierBottom != other.barrierBottom) {
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
