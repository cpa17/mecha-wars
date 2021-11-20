package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

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

    public Gear(int xcoor, int ycoor, int direction, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.direction = direction;

        if(!isTest) {
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

    /**
     * Take the Direction of the rotation of the Gear.
     * @return the Direction
     */
    public int getDirection() {
        return this.direction;
    }

    public Texture getTile() {
        return this.tile;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Gear other = (Gear) obj;
        if (xcoor != other.xcoor) return false;
        if (ycoor != other.ycoor) return false;
        if (direction != other.direction) return false;

        return true;
    }
}
