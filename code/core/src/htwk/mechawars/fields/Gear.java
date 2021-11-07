package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Gear.
 */
public class Gear extends Field {

    // Attribut was angibt in welche Richtung sich das Zahnrad dreht
    // 1 = rechtsrum, 2 = linksrum
    private int direction;
    private Texture tile;

    /**
     * Constructor of a Gear.
     */
    public Gear(int xcoor, int ycoor, int direction) {
        super(xcoor, ycoor);
        this.direction = direction;
        
        if(direction == 1) {
            this.tile = new Texture("mapAssets/gear/Gear01.png");
        }
        else {
            this.tile = new Texture("mapAssets/gear/Gear02.png");
        }
    }

    @Override
    public String showAttributes() {
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

    @Override
    public Texture getTile() {
        return this.tile;
    }
}
