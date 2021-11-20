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

    public Field(int xcoor, int ycoor, boolean isTest) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        if (!isTest) {
            this.tile = new Texture("mapAssets/StandardField.png");
        }
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

    public Texture getTile() {
        return this.tile;
    }
    
    public Robot action(Robot robot) {
        return robot; 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        StandardField other = (StandardField) obj;
        if (xcoor != other.xcoor) return false;
        if (ycoor != other.ycoor) return false;

        return true;
    }
}
