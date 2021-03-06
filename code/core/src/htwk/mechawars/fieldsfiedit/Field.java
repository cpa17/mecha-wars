package htwk.mechawars.fieldsfiedit;

import com.badlogic.gdx.graphics.Texture;

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

    /**
     * Constructor of a Field which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
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

    public int getXcoor() {
        return this.xcoor;
    }

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
        if (ycoor != other.ycoor) {
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
