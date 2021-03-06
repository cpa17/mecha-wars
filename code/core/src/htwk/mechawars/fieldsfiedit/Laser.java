package htwk.mechawars.fieldsfiedit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Laser.
 */
public class Laser extends Field {

    // attribute which stands for the type of a laser
    // 0 = begin left, 1 = begin top, 2 = begin right, 3 = begin bottom,
    // 4 = center horizontal, 5 = center vertical,
    // 6 = end left, 7 = end top, 8 = end right, 9 = end bottom
    private int type;
    private Texture tile;

    /**
     * Constructor of a Laser.
     */
    public Laser(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
        this.type = type;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "laser/"
                + "Laser0" + String.valueOf(type) + ".png"));
    }

    /**
     * Constructor of a Laser which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public Laser(int xcoor, int ycoor, int type, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.type = type;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "laser/"
                    + "Laser0" + String.valueOf(type) + ".png"));
        }
    }

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", type: " + this.type;
        return attributes;
    }

    public int getType() {
        return this.type;
    }

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

        Laser other = (Laser) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
            return false;
        }
        if (type != other.type) {
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
