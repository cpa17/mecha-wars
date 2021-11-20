package htwk.mechawars.fields;

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
        this.tile = new Texture(Gdx.files.internal("mapAssets/" +
                        "laser/" + "Laser0" + String.valueOf(type) + ".png"));
    }

    public Laser(int xcoor, int ycoor, int type, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.type = type;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" +
                    "laser/" + "Laser0" + String.valueOf(type) + ".png"));
        }
    }

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", type: " + this.type;
        return attributes;
    }

    /**
     * Take what Type of laser piece it is.
     * @return the Type
     */
    public int getType() {
        return this.type;
    }

    public Texture getTile() {
        return this.tile;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Laser other = (Laser) obj;
        if (xcoor != other.xcoor) return false;
        if (ycoor != other.ycoor) return false;
        if (type != other.type) return false;

        return true;
    }
}
