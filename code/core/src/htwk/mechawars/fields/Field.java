package htwk.mechawars.fields;

/**
 * Class of a Field.
 */
public class Field {

    protected int xcoor;
    protected int ycoor;

    /**
     * Constructor of a Field.
     */
    public Field(int xcoor, int ycoor) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
    }

    public String showAttributes() {
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
    
    public void rotateImg() {
        
    }
}
