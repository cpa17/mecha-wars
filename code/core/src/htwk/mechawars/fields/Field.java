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

    /**
     * Constructor of a Field.
     */
    public Field(int xcoor, int ycoor) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.tile = new Texture("mapAssets/StandardField.png");
    }

    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor;
        return attributes;
    }

    /**
     * Take the x coordinate.
     * @return xcoor The x coordinate
     */
    public int getXcoor() {
        return this.xcoor;
    }

    /**
     * Take the y coordinate.
     * @return ycoor The y coordinate
     */
    public int getYcoor() {
        return this.ycoor;
    }

    /**
     * Dummy.
     * 
     * @return .
     */
    public Texture getTile() {
        return this.tile;
    }
    
    /**
     * On the Field, a robot action can be done.
     * 
     * @param robot
     * @return the instance robot
     */
    public Robot action(Robot robot) {        
        return robot; 
    }
}
